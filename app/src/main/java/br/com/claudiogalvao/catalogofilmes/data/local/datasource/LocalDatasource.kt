package br.com.claudiogalvao.catalogofilmes.data.local.datasource

import br.com.claudiogalvao.catalogofilmes.data.local.bd.dao.FilmeDAO
import br.com.claudiogalvao.catalogofilmes.data.mapper.FilmesMapper
import br.com.claudiogalvao.catalogofilmes.domain.callback.FilmesCallback

class LocalDatasource(val dao: FilmeDAO) {

    fun listaFilmes(callback: FilmesCallback) {
        val filmes = dao.getAll().map {
            FilmesMapper.mapFilmesBD(it)!!
        }
        callback.onSuccess(filmes)
    }
}