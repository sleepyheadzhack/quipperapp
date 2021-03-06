package tyo.android.com.quipperapp.videoPlaylist.network

import retrofit2.Call
import retrofit2.http.GET

interface PlaylistService {
    @GET("native-technical-exam/playlist.json")
    fun getPlaylist(): Call<List<ResponsePlaylist>>
}