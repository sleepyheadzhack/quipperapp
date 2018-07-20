package tyo.android.com.quipperapp.videoPlaylist.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkApi {
    companion object {
        val playlistService: PlaylistService by lazy {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://quipper.github.io").build()
            retrofit.create(PlaylistService::class.java)
        }
    }
}
