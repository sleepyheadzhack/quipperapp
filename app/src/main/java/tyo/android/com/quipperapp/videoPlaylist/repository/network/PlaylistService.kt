package tyo.android.com.quipperapp.videoPlaylist.repository.network

import retrofit2.Call
import retrofit2.http.GET

interface PlaylistService {
    @GET("native-technical-exam/playlist.json")
    fun getPlaylist(): Call<List<ResponseVideo>>
}