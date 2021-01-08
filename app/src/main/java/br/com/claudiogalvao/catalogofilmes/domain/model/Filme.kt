package br.com.claudiogalvao.catalogofilmes.domain.model

import java.io.Serializable

class Filme constructor(val id: Int,
            val title: String,
            val original_title: String,
            val original_language: String,
            val release_date: String,
            val popularity: Double,
            val vote_count: Int,
            val vote_average: Double,
            val adult: Boolean,
            val video: Boolean,
            val genre_ids: Array<Int> = emptyArray(),
            val overview: String,
            val backdrop_path: String,
            val poster_path: String,
            var isFavorite: Boolean = false) : Serializable {

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

    fun setIsFavorite(isFavorite: Boolean) {
        this.isFavorite = isFavorite
    }

    override fun toString(): String {
        return "$title - $release_date - $vote_average"
    }

}