package tyo.android.com.quipperapp

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import tyo.android.com.quipperapp.videoPlaylist.repository.local.PlaylistDao
import tyo.android.com.quipperapp.videoPlaylist.repository.local.PlaylistDatabase
import tyo.android.com.quipperapp.videoPlaylist.repository.local.Video

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
    fun insertOneVideoThenFindAll() {
        val video = Video(1,
                "video_title",
                "presenter_name",
                "description",
                "thumbnail_url",
                "video_url",
                5000)

        playlistDatabase.playlistDao().insert(video)
        val playlists = playlistDatabase.playlistDao().findAll().value
        assert(playlists?.size == 1)
        assert(playlists?.contains(video) ?: false)
    }

    @Test
    fun insertNothingThenFindAll() {
        val playlists = playlistDatabase.playlistDao().findAll().value
        assert(playlists == null)
    }
}
