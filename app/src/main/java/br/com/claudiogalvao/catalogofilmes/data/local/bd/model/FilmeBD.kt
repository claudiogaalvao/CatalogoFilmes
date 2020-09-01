package br.com.claudiogalvao.catalogofilmes.data.local.bd.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filmes")
data class FilmeBD(@PrimaryKey val id: Int,
                   val title: String,
                   val original_title: String,
                   val original_language: String,
                   val release_date: String,
                   val popularity: Double,
                   val vote_count: Int,
                   val vote_average: Double,
                   val adult: Boolean,
                   val video: Boolean,
                   val genre_ids: Int = 0,
                   val overview: String,
                   val backdrop_path: String,
                   val poster_path: String)