package br.com.claudiogalvao.catalogofilmes.model

import br.com.claudiogalvao.catalogofilmes.R
import java.util.*

class Filme(private val id: Int,
            val title: String,
            private val original_title: String,
            private val original_language: String,
            private val release_date: String, val popularity: Double,
            private val vote_count: Int,
            private val vote_average: Double,
            private val adult: Boolean,
            private val video: Boolean,
            private val genre_ids: Array<Int>,
            private val overview: String,
            private val backdrop_path: String,
            private val poster_path: String) {

    override fun toString(): String {
        return "$title - $release_date - $vote_average"
    }


}