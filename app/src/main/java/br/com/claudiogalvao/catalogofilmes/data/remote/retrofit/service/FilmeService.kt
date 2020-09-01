package br.com.claudiogalvao.catalogofilmes.data.remote.retrofit.service

import br.com.claudiogalvao.catalogofilmes.model.RetornoRequisicao
import retrofit2.Call
import retrofit2.http.GET

interface FilmeService {

    @GET("popular?api_key=696a8a555845ed882929281feba3dabf&page=1&language=pt-BR")
    fun buscaFilmesPopulares() : Call<RetornoRequisicao>

}