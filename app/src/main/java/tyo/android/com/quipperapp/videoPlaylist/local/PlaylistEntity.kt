package tyo.android.com.quipperapp.videoPlaylist.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "playlist")
data class PlaylistEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long,
        val title: String,
        val presenterName: String,
        val description: String,
        val thumbnailUrl: String,
        val videoUrl: String,
        val videoDuration: Long
)
