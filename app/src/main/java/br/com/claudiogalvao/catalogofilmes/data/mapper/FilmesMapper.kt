package br.com.claudiogalvao.catalogofilmes.data.mapper

import br.com.claudiogalvao.catalogofilmes.data.local.bd.model.FilmeBD
import br.com.claudiogalvao.catalogofilmes.domain.model.Filme

object FilmesMapper {

    fun mapFilmesBD(filmeBD: FilmeBD) : Filme {
        var filme = Filme(filmeBD.id, filmeBD.title, filmeBD.original_title,
            filmeBD.original_language, filmeBD.release_date, filmeBD.popularity,
            filmeBD.vote_count, filmeBD.vote_average, filmeBD.adult, filmeBD.video,
            emptyArray(), filmeBD.overview, filmeBD.backdrop_path, filmeBD.poster_path)

        return filme;
    }
}