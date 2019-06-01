package com.jamesvanhallen.movierater.model.database.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jamesvanhallen.movierater.model.database.MoviesDataBase.Companion.MOVIES_TABLE_NAME

@Entity(tableName = MOVIES_TABLE_NAME)
data class Movie(
    @PrimaryKey @ColumnInfo(name = ID) val id: Long,
    @ColumnInfo(name = NAME) val name: String,
    @ColumnInfo(name = RATIO) val ratio: Int,
    @ColumnInfo(name = IMAGE) val image: String
) {
    companion object {
        const val ID = "_id"
        const val NAME = "name"
        const val RATIO = "ratio"
        const val IMAGE = "image"
    }
}