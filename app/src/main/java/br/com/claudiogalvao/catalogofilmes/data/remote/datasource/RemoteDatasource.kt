package br.com.claudiogalvao.catalogofilmes.data.remote.datasource

import android.content.Context
import br.com.claudiogalvao.catalogofilmes.data.remote.retrofit.RetrofitInitializer
import br.com.claudiogalvao.catalogofilmes.domain.callback.FilmesCallback
import br.com.claudiogalvao.catalogofilmes.model.RetornoRequisicao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDatasource(val context: Context) {

    fun listaFilmes(callback: FilmesCallback) {
        val call = RetrofitInitializer(context).filmeService().buscaFilmesPopulares()

        call.enqueue(object : Callback<RetornoRequisicao> {
            override fun onFailure(call: Call<RetornoRequisicao>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(
                call: Call<RetornoRequisicao>,
                response: Response<RetornoRequisicao>
            ) {
                response.body()?.let {
                    callback.onSuccess(it.getResults())
                }?:callback.onSuccess(listOf())
            }
        })
    }

}