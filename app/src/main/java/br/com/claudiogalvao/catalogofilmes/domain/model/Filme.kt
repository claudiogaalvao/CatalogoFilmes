package br.com.claudiogalvao.catalogofilmes.domain.model

import java.io.Serializable

class Filme(private val id: Int,
            private val title: String,
            private val original_title: String,
            private val original_language: String,
            private val release_date: String,
            private val popularity: Double,
            private val vote_count: Int,
            private val vote_average: Double,
            private val adult: Boolean,
            private val video: Boolean,
            private val genre_ids: Array<Int>,
            private val overview: String,
            private val backdrop_path: String,
            private val poster_path: String) : Serializable {

    fun getCapa() : String {
        return "https://image.tmdb.org/t/p/w500$poster_path"
    }

    fun getTitulo() : String {
        return title
    }

    fun getDataDeLancamento() : String {
        return release_date
    }

    fun getAvaliacao() : String {
        return "$vote_average/10"
    }

    fun getSinopse() : String {
        return overview
    }

    override fun toString(): String {
        return "$title - $release_date - $vote_average"
    }

}