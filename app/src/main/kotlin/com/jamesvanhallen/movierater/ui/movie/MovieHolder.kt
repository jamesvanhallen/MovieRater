package com.jamesvanhallen.movierater.ui.movie

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jamesvanhallen.movierater.model.database.movie.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.holder_movie.*

class MovieHolder(
    override val containerView: View,
    val onRatioChangeListener: (movie: Movie) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    lateinit var movie: Movie

    init {
        ratioBar.run {
            setOnRatingBarChangeListener { _, ratio, _ ->
                movie.ratio = ratio
                onRatioChangeListener(movie)
            }
        }
    }

    fun bind(movie: Movie) {
        this.movie = movie
        tvName.text = movie.name
        ratioBar.rating = movie.ratio
        Picasso.with(tvName.context)
            .load(movie.image)
            .fit()
            .centerCrop()
            .into(ivCover)
    }
}