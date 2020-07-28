package br.com.claudiogalvao.catalogofilmes.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.claudiogalvao.catalogofilmes.R
import br.com.claudiogalvao.catalogofilmes.model.Filme
import br.com.claudiogalvao.catalogofilmes.model.RetornoRequisicao
import br.com.claudiogalvao.catalogofilmes.retrofit.RetrofitInitializer
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        carregaFilmesNaLista()
    }

    private fun carregaFilmesNaLista() {
        val call = RetrofitInitializer().filmeService().buscaFilmesPopulares()

        call.enqueue(object : Callback<RetornoRequisicao> {
            override fun onFailure(call: Call<RetornoRequisicao>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Falha ao carregar lista de filmes...",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onResponse(
                call: Call<RetornoRequisicao>,
                response: Response<RetornoRequisicao>
            ) {
                response.body()?.let {
                    populaListaDeFilmes(it)
                    configuraLista()
                }
            }
        })
    }

    private fun populaListaDeFilmes(it: RetornoRequisicao) {
        var listaFilmes: ArrayList<Filme> = it.getResults()
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
        var intent = Intent(this.requireContext(), DetalhesFilmeActivity::class.java)
        intent.putExtra("filme", filme)
        startActivity(intent)
    }

}
