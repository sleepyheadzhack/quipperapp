package tyo.android.com.quipperapp.videoPlaylist.repository.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Video::class], version = 1, exportSchema = false)
abstract class PlaylistDatabase: RoomDatabase() {

    abstract fun playlistDao(): PlaylistDao

    companion object {
        private var instance: PlaylistDatabase? = null

        fun getInstance(context: Context): PlaylistDatabase {
            return instance ?: Room.databaseBuilder(
                        context.applicationContext,
                        PlaylistDatabase::class.java,
                        "playlist_database")
                    .build()
                    .also { instance = it }
        }
    }
}
