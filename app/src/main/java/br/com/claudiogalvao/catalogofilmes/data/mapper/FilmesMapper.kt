package br.com.claudiogalvao.catalogofilmes.data.mapper

import br.com.claudiogalvao.catalogofilmes.data.local.bd.model.FilmeBD
import br.com.claudiogalvao.catalogofilmes.domain.model.Filme

object FilmesMapper {

    fun mapFilmesBD(filmeBD: FilmeBD) : Filme {
        var filme = Filme(filmeBD.id, filmeBD.title, filmeBD.original_title,
            filmeBD.original_language, filmeBD.release_date, filmeBD.popularity,
            filmeBD.vote_count, filmeBD.vote_average, filmeBD.adult, filmeBD.video,
            emptyArray(), filmeBD.overview, filmeBD.backdrop_path, filmeBD.poster_path, filmeBD.isFavorite)

        return filme;
    }

    fun mapFilmes(filme: Filme): FilmeBD {
        var filmeBD = FilmeBD(filme.id, filme.title, filme.original_title,
            filme.original_language, filme.release_date, filme.popularity,
            filme.vote_count, filme.vote_average, filme.adult, filme.video,
            0, filme.overview, filme.backdrop_path, filme.poster_path, filme.isFavorite)

        return filmeBD
    }
}