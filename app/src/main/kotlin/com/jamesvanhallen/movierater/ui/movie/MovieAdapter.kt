package com.jamesvanhallen.movierater.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jamesvanhallen.movierater.R
import com.jamesvanhallen.movierater.model.database.movie.Movie
import com.jamesvanhallen.movierater.utils.MoviesDiffCallback

class MovieAdapter(
    private val onRatioChangeListener: (movie: Movie) -> Unit
) : RecyclerView.Adapter<MovieHolder>() {

    var items = ArrayList<Movie>()
        set(value) {
            val oldItems = ArrayList(field)
            field = value
            if (oldItems.isNotEmpty()) {
                val diffResult = DiffUtil.calculateDiff(MoviesDiffCallback(oldItems, field))
                diffResult.dispatchUpdatesTo(this)
            } else {
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.holder_movie, parent, false)
        return MovieHolder(view, onRatioChangeListener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(items[position])
    }
}