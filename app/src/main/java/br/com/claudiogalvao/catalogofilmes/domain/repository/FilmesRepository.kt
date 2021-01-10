package br.com.claudiogalvao.catalogofilmes.domain.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import br.com.claudiogalvao.catalogofilmes.data.local.datasource.LocalDatasource
import br.com.claudiogalvao.catalogofilmes.data.remote.datasource.RemoteDatasource
import br.com.claudiogalvao.catalogofilmes.domain.callback.FilmesCallback
import br.com.claudiogalvao.catalogofilmes.domain.model.Filme

class FilmesRepository(private val context: Context,
                       private val datasourceLocal: LocalDatasource,
                       private val datasourceRemoto: RemoteDatasource) {

    fun listaFilmes(callback: FilmesCallback, carregarCash: Boolean = true) {
        if(carregarCash) {
            carregarDadosLocal(callback)
        } else {
            carregarDadosRemoto(callback)
        }
    }

    fun atualizaFilme(filme: Filme) {
        datasourceLocal.atualizarFilme(filme)
    }

    private fun carregarDadosLocal(callback: FilmesCallback) {
        datasourceLocal.listaFilmes(object : FilmesCallback {
            override fun onSuccess(listaFilmes: List<Filme>) {
                callback.onSuccess(listaFilmes)
                Log.i("room", "Dados do cash carregados")
                datasourceRemoto.listaFilmes(callback)
                Log.i("room", "Buscando dados remoto...")
            }

            override fun onError(e: Throwable) {
                Log.i("room", "Falha ao carregar dados do cash")
                datasourceRemoto.listaFilmes(callback)
                Log.i("room", "Tentando buscar dados remoto...")
            }

        })
    }

    private fun carregarDadosRemoto(callback: FilmesCallback) {
        datasourceRemoto.listaFilmes(callback)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun hasNetwork() : Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }
}