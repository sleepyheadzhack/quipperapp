package tyo.android.com.quipperapp

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import tyo.android.com.quipperapp.videoPlaylist.local.PlaylistDao
import tyo.android.com.quipperapp.videoPlaylist.local.PlaylistDatabase
import tyo.android.com.quipperapp.videoPlaylist.local.PlaylistEntity

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    lateinit var playlistDao: PlaylistDao
    lateinit var playlistDatabase: PlaylistDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        playlistDatabase = Room.inMemoryDatabaseBuilder(context, PlaylistDatabase::class.java).build()
        playlistDao = playlistDatabase.playlistDao()
    }

    @After
    fun closeDb() {
        playlistDatabase.close()
    }

    @Test
    fun insertAndFindAll() {
        val entity = PlaylistEntity(1,
                "title",
                "presentername",
                "desc",
                "thumbnailUrl",
                "videoUrl",
                5000)

        playlistDatabase.playlistDao().insert(entity)
        val playlists = playlistDatabase.playlistDao().findAll()
        assert(playlists.size == 1)
        assert(playlists[0] == entity)
    }
}
