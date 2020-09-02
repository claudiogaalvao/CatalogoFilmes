package br.com.claudiogalvao.catalogofilmes.data.remote.datasource

import android.content.Context
import android.util.Log
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
                Log.i("room", "Falha ao carregar dados remoto")
                callback.onError(t)
            }

            override fun onResponse(
                call: Call<RetornoRequisicao>,
                response: Response<RetornoRequisicao>
            ) {
                Log.i("room", "Dados remoto carregados com sucesso")
                response.body()?.let {
                    callback.onSuccess(it.getResults())
                }?:callback.onSuccess(listOf())
            }
        })
    }

}