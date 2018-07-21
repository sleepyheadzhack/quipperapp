package tyo.android.com.quipperapp.videoPlaylist.repository.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PlaylistDao {
    @Insert
    fun insert(video: Video)

    @Query("SELECT * FROM playlist ORDER BY title ASC")
    fun findAll(): LiveData<List<Video>>
}
