package tyo.android.com.quipperapp.videoPlaylist.repository.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "playlist")
data class Video(
        @NonNull
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "presenter_name")
        val presenterName: String,

        @ColumnInfo(name = "description")
        val description: String,

        @ColumnInfo(name = "thumbnail_url")
        val thumbnailUrl: String,

        @ColumnInfo(name = "video_url")
        val videoUrl: String,

        @ColumnInfo(name = "video_duration")
        val videoDuration: Long
)
