package br.com.claudiogalvao.catalogofilmes.domain.usecase

import br.com.claudiogalvao.catalogofilmes.domain.callback.FilmesCallback
import br.com.claudiogalvao.catalogofilmes.domain.repository.FilmesRepository

class ListaFilmesCasoDeUso(private val repository: FilmesRepository) {

    fun executar(callback: FilmesCallback, carregarCash: Boolean = true) {
        repository.listaFilmes(callback, carregarCash)
    }
}