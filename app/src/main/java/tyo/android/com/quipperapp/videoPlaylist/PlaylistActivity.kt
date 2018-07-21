package tyo.android.com.quipperapp.videoPlaylist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_playlist.*
import tyo.android.com.quipperapp.R
import tyo.android.com.quipperapp.videoPlaylist.repository.local.Video

class PlaylistActivity : AppCompatActivity() {

    var videos: List<Video> = emptyList()
    private lateinit var viewModel: PlaylistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)
        viewModel = ViewModelProviders.of(this).get(PlaylistViewModel::class.java)
        recyclerPlaylist.layoutManager = LinearLayoutManager(this)
        recyclerPlaylist.adapter = playlistAdapter

        viewModel.playlist().observe(this, Observer { videos ->
            this.videos = videos ?: this.videos
            playlistAdapter.notifyDataSetChanged()
        })

        viewModel.error().observe(this, Observer { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        })
    }

    private val playlistAdapter = object: RecyclerView.Adapter<VideoViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_view_video_playlist, parent, false)
            return VideoViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return videos.size
        }

        override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
            val video = videos[position]
            Picasso.get().load(video.thumbnailUrl).into(holder.imageVideo)
            holder.textTitle.text = video.title
            holder.textPresenter.text = video.presenterName
            holder.textDescription.text = video.description
            val minutes = video.videoDuration / (60 * 1000)
            val seconds = video.videoDuration/1000 - (minutes * 60)
            holder.textDuration.text = String.format("%02d : %02d", minutes, seconds)
        }
    }

    class VideoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageVideo = itemView.findViewById(R.id.imageVideo) as ImageView
        val textTitle = itemView.findViewById(R.id.textTitle) as TextView
        val textPresenter = itemView.findViewById(R.id.textPresenter) as TextView
        val textDescription = itemView.findViewById(R.id.textDescription) as TextView
        val textDuration = itemView.findViewById(R.id.textDuration) as TextView
    }
}
