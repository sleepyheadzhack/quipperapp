package tyo.android.com.quipperapp.videoPlaylist.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(PlaylistEntity::class), version = 1, exportSchema = false)
abstract class PlaylistDatabase: RoomDatabase() {

    abstract fun playlistDao(): PlaylistDao

    companion object {
        var instance: PlaylistDatabase? = null
        fun getInstance(context: Context): PlaylistDatabase {
            return instance ?: Room.databaseBuilder(context.applicationContext,
                    PlaylistDatabase::class.java,
                    "playlist_database").build()
        }
    }
}
