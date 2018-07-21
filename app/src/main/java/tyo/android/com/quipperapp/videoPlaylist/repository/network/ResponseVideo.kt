package tyo.android.com.quipperapp.videoPlaylist.repository.network

import com.google.gson.annotations.SerializedName

data class ResponseVideo(
        @SerializedName("title")
        val title: String,

        @SerializedName("presenter_name")
        val presenterName: String,

        @SerializedName("description")
        val description: String,

        @SerializedName("thumbnail_url")
        val thumbnailUrl: String,

        @SerializedName("video_url")
        val videoUrl: String,

        @SerializedName("video_duration")
        val videoDuration: Long
)
