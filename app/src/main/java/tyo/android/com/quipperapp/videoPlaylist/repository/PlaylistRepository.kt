package tyo.android.com.quipperapp.videoPlaylist.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tyo.android.com.quiperapp.common.SingleLiveEvent
import tyo.android.com.quipperapp.videoPlaylist.repository.local.PlaylistDao
import tyo.android.com.quipperapp.videoPlaylist.repository.local.Video
import tyo.android.com.quipperapp.videoPlaylist.repository.network.PlaylistService
import tyo.android.com.quipperapp.videoPlaylist.repository.network.ResponseVideo
import java.net.UnknownHostException

class PlaylistRepository(playlistDao: PlaylistDao, playlistService: PlaylistService) {

    val error : SingleLiveEvent<String> = SingleLiveEvent()

    private val playlistDao = playlistDao
    private val playlistService = playlistService
    private val playlist: MediatorLiveData<List<Video>> = MediatorLiveData()

    fun loadPlaylist(): LiveData<List<Video>> {
        playlist.let {
            val dbSource = loadFromLocalDB()
            playlist.addSource(dbSource) { videos ->
                if (videos?.size == 0) {
                    val netSource = loadFromNetwork()
                    playlist.addSource(netSource){ videos ->
                        playlist.postValue(videos)
                        playlist.removeSource(netSource)
                    }
                } else {
                    playlist.postValue(videos)
                    playlist.removeSource(dbSource)
                }
            }
        }
        return playlist
    }

    private fun loadFromLocalDB(): LiveData<List<Video>> {
        Log.d("TEST", "db access here")
        return doAsyncResult {
            playlistDao.findAll()
        }.get()
    }

    private fun loadFromNetwork(): LiveData<List<Video>> {
        Log.d("TEST", "network access here")
        val networkSource = MutableLiveData<List<Video>>()

        playlistService.getPlaylist().enqueue(object: Callback<List<ResponseVideo>> {
            override fun onFailure(call: Call<List<ResponseVideo>>?, t: Throwable?) {
                if (t is UnknownHostException) error.postValue("Not connected to Internet")
            }

            override fun onResponse(call: Call<List<ResponseVideo>>?, response: Response<List<ResponseVideo>>?) {
                if (response?.isSuccessful == true) {
                    val videos = response.body()?.map {
                        Video(0,
                            it.title,
                            it.presenterName,
                            it.description,
                            it.thumbnailUrl,
                            it.videoUrl,
                            it.videoDuration
                        ).also {
                            video -> doAsync {
                                playlistDao.insert(video)
                            }
                        } }
                    networkSource.postValue(videos)
                }
            }
        })

        return networkSource
    }
}
