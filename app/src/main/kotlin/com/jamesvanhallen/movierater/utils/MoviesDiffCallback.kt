package com.jamesvanhallen.movierater.utils

import androidx.recyclerview.widget.DiffUtil
import com.jamesvanhallen.movierater.model.database.movie.Movie

class MoviesDiffCallback(
    private val oldItems: List<Movie>,
    private val newItems: List<Movie>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id == newItems[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return eqContent(oldItems[oldItemPosition], newItems[newItemPosition])
    }

    private fun eqContent(first: Movie, second: Movie): Boolean {
        return first.ratio == second.ratio
    }
}