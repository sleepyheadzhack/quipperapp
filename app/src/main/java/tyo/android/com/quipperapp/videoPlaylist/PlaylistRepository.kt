package tyo.android.com.quipperapp.videoPlaylist

import android.arch.lifecycle.LiveData
import android.content.Context
import tyo.android.com.quipperapp.videoPlaylist.local.PlaylistDatabase
import tyo.android.com.quipperapp.videoPlaylist.local.PlaylistEntity
import tyo.android.com.quipperapp.videoPlaylist.network.NetworkApi

class PlaylistRepository(_context: Context) {

    val context = _context
    lateinit var cached: LiveData<List<PlaylistEntity>>

    fun fetchPlaylist(): LiveData<List<PlaylistEntity>> {
        val playlist = NetworkApi.playlistService.getPlaylist().execute().body()
        val playlistDao = PlaylistDatabase.getInstance(context).playlistDao()
        playlist?.forEach {
            val entity = PlaylistEntity(
                    1L,
                    it.title,
                    it.presenterName,
                    it.description,
                    it.thumbnailUrl,
                    it.videoUrl,
                    it.videoDuration
            )
            playlistDao.insert(entity)
        }
        cached = playlistDao.findAll()
        return cached
    }

}
