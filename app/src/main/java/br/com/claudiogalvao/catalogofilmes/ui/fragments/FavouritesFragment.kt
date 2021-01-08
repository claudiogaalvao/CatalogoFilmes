package br.com.claudiogalvao.catalogofilmes.ui.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi

import br.com.claudiogalvao.catalogofilmes.R
import br.com.claudiogalvao.catalogofilmes.di.FilmesModule
import br.com.claudiogalvao.catalogofilmes.domain.callback.FilmesCallback
import br.com.claudiogalvao.catalogofilmes.domain.model.Filme
import br.com.claudiogalvao.catalogofilmes.ui.adapter.ListaFilmesAdapter
import br.com.claudiogalvao.catalogofilmes.ui.adapter.OnClickListener
import kotlinx.android.synthetic.main.fragment_favourites.*
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class FavouritesFragment : Fragment() {
    var filmes: ArrayList<Filme> = arrayListOf()
    val onClickListener: OnClickListener = object : OnClickListener {
        override fun onClick(filme: Filme) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        carregaFilmesNaLista()
        configuraLista()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun carregaFilmesNaLista() {
        FilmesModule.filmesCasoDeUso.executar(object : FilmesCallback {
            override fun onSuccess(listaFilmes: List<Filme>) {
                if(listaFilmes.isNotEmpty()) {
                    populaListaDeFilmes(listaFilmes)
                    configuraLista()
                }
            }

            override fun onError(e: Throwable) {
                Toast.makeText(
                    context,
                    "Sem conexão...",
                    Toast.LENGTH_LONG
                ).show()
            }
        }, true)
    }

    private fun populaListaDeFilmes(listaFilmes: List<Filme>) {/*
        Log.i("filme", "Lista de filmes retornados: ${listaFilmes.size}")
        // APAGAR
        for (i in 0 until 10) {
            if(i < 3) {
                listaFilmes.elementAt(i).setIsFavorite(true)
                Log.i("filme", "Filme adicionado como favorito ${i}")
            }
        }*/
        // Manter
        for(filme in listaFilmes) {
            if(filme.isFavorite) {
                Log.i("filme", "Filme favorito: ${filme.getTitulo()}")
                filmes.add(filme)
            }
        }
    }

    private fun configuraLista() {
        val adapter = ListaFilmesAdapter(this.requireContext(), filmes)

        adapter.onClickListener = onClickListener

        activity_filmes_favoritos_gridview.adapter = adapter
    }

}
