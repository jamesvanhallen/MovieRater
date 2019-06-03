package com.jamesvanhallen.movierater.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jamesvanhallen.movierater.R
import com.jamesvanhallen.movierater.model.database.movie.Movie

class MovieAdapter(
    private val onRatioChangeListener: (Movie) -> Unit
) : RecyclerView.Adapter<MovieHolder>() {

    var inflater: LayoutInflater? = null

    var items = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }
        inflater?.let {
            val view = it.inflate(R.layout.holder_movie, parent, false)
            return MovieHolder(view, onRatioChangeListener)
        }
        throw NullPointerException("inflater is null")
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(items[position])
    }
}