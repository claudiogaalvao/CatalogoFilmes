package br.com.claudiogalvao.catalogofilmes.domain.callback

import br.com.claudiogalvao.catalogofilmes.domain.model.Filme

interface FilmesCallback {

    fun onSuccess(listaFilmes: List<Filme>)
    fun onError(e: Throwable)
}