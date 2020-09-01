package br.com.claudiogalvao.catalogofilmes.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import br.com.claudiogalvao.catalogofilmes.MyApplication
import br.com.claudiogalvao.catalogofilmes.R
import br.com.claudiogalvao.catalogofilmes.domain.model.Filme
import br.com.claudiogalvao.catalogofilmes.model.RetornoRequisicao
import br.com.claudiogalvao.catalogofilmes.data.remote.retrofit.RetrofitInitializer
import br.com.claudiogalvao.catalogofilmes.di.FilmesModule
import br.com.claudiogalvao.catalogofilmes.domain.callback.FilmesCallback
import br.com.claudiogalvao.catalogofilmes.ui.activity.DetalhesFilmeActivity
import br.com.claudiogalvao.catalogofilmes.ui.adapter.ListaFilmesAdapter
import br.com.claudiogalvao.catalogofilmes.ui.adapter.OnClickListener
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    var filmes: ArrayList<Filme> = arrayListOf()
    val onClickListener: OnClickListener = object : OnClickListener {
        override fun onClick(filme: Filme) {
            chamaDetalhesFilme(filme)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        carregaFilmesNaLista()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun carregaFilmesNaLista() {
        FilmesModule.filmesCasoDeUso.executar(object : FilmesCallback {
            override fun onSuccess(listaFilmes: List<Filme>) {
                populaListaDeFilmes(listaFilmes)
                configuraLista()
            }

            override fun onError(e: Throwable) {
                Toast.makeText(
                    context,
                    "Sem conexão...",
                    Toast.LENGTH_LONG
                ).show()
            }
        }, false)
    }

    private fun populaListaDeFilmes(listaFilmes: List<Filme>) {
        for (filme in listaFilmes) {
            filmes.add(filme)
        }
    }

    private fun configuraLista() {
        val adapter = ListaFilmesAdapter(this.requireContext(), filmes)

        adapter.onClickListener = onClickListener

        activity_lista_filmes_gridview.adapter = adapter

    }

    private fun chamaDetalhesFilme(filme: Filme) {
        val intent = Intent(this.requireContext(), DetalhesFilmeActivity::class.java)
        intent.putExtra("filme", filme)
        startActivity(intent)
    }

}
