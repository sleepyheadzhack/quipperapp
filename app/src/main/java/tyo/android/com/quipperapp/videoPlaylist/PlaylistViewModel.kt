package tyo.android.com.quipperapp.videoPlaylist

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import tyo.android.com.quipperapp.videoPlaylist.repository.PlaylistRepository
import tyo.android.com.quipperapp.videoPlaylist.repository.local.PlaylistDatabase
import tyo.android.com.quipperapp.videoPlaylist.repository.network.NetworkApi

class PlaylistViewModel(application: Application) : AndroidViewModel(application) {
    private val playlistRepository by lazy {
        val playlistDao = PlaylistDatabase.getInstance(application).playlistDao()
        val playlistService = NetworkApi.playlistService
        PlaylistRepository(playlistDao, playlistService)
    }

    fun playlist() = playlistRepository.loadPlaylist()
    fun error() = playlistRepository.error
}
