package tyo.android.com.quipperapp.videoPlaylist

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import org.jetbrains.anko.doAsync
import tyo.android.com.quipperapp.videoPlaylist.local.PlaylistEntity

class PlaylistViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PlaylistRepository(application)
    private var playlist: LiveData<List<PlaylistEntity>>? = null

    fun getPlaylist(): LiveData<List<PlaylistEntity>>? {
        doAsync {
             repository.fetchPlaylist().also {
                 playlist = it
             }
        }
        return playlist
    }
}
