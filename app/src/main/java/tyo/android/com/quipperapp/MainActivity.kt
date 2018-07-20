package tyo.android.com.quipperapp

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import tyo.android.com.quipperapp.videoPlaylist.PlaylistViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: PlaylistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)

        val playlist = viewModel.getPlaylist()
        playlist?.value?.forEach {
            Log.d("TEST", it.title)
        }
    }
}
