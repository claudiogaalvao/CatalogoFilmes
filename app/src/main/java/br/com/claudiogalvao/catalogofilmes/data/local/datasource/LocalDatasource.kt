package br.com.claudiogalvao.catalogofilmes.data.local.datasource

import br.com.claudiogalvao.catalogofilmes.data.local.bd.dao.FilmeDAO
import br.com.claudiogalvao.catalogofilmes.data.mapper.FilmesMapper
import br.com.claudiogalvao.catalogofilmes.domain.callback.FilmesCallback
import br.com.claudiogalvao.catalogofilmes.domain.model.Filme

class LocalDatasource(val dao: FilmeDAO) {

    fun listaFilmes(callback: FilmesCallback) {
        val filmes = dao.getAll().map {
            FilmesMapper.mapFilmesBD(it)!!
        }
        callback.onSuccess(filmes)
    }

    fun listaFilmesFavoritos(callback: FilmesCallback) {
        val filmes = dao.getFavoriteMovies().map {
            FilmesMapper.mapFilmesBD(it)
        }
        callback.onSuccess(filmes)
    }

    fun atualizarFilme(filme: Filme) {
        dao.setIsFavorite(filme.isFavorite, filme.id)
    }
}