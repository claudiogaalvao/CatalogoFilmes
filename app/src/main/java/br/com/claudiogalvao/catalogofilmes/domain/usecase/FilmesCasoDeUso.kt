package br.com.claudiogalvao.catalogofilmes.domain.usecase

import br.com.claudiogalvao.catalogofilmes.domain.callback.FilmesCallback
import br.com.claudiogalvao.catalogofilmes.domain.model.Filme
import br.com.claudiogalvao.catalogofilmes.domain.repository.FilmesRepository

class FilmesCasoDeUso(private val repository: FilmesRepository) {

    fun executar(callback: FilmesCallback, carregarCash: Boolean = true) {
        repository.listaFilmes(callback, carregarCash)
    }

    fun getFilmesFavoritos(callback: FilmesCallback) {
        repository.listaFilmesFavoritos(callback)
    }

    fun atualizar(filme: Filme) {
        repository.atualizaFilme(filme)
    }
}