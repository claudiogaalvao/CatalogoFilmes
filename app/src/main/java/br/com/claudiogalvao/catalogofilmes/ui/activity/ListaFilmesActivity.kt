package br.com.claudiogalvao.catalogofilmes.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.claudiogalvao.catalogofilmes.R
import br.com.claudiogalvao.catalogofilmes.model.Filme
import br.com.claudiogalvao.catalogofilmes.model.RetornoRequisicao
import br.com.claudiogalvao.catalogofilmes.retrofit.RetrofitInitializer
import br.com.claudiogalvao.catalogofilmes.ui.adapter.ListaFilmesAdapter
import br.com.claudiogalvao.catalogofilmes.ui.adapter.OnClickListener
import kotlinx.android.synthetic.main.activity_lista_filmes.*
import kotlinx.android.synthetic.main.filme_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaFilmesActivity : AppCompatActivity() {
    var filmes: ArrayList<Filme> = arrayListOf()
    val onClickListener: OnClickListener = object : OnClickListener {
        override fun onClick(filme: Filme) {
            chamaDetalhesFilme(filme)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)

        carregaFilmesNaLista()
    }

    private fun carregaFilmesNaLista() {
        val call = RetrofitInitializer().filmeService().buscaFilmesPopulares()

        call.enqueue(object : Callback<RetornoRequisicao> {
            override fun onFailure(call: Call<RetornoRequisicao>, t: Throwable) {
                Toast.makeText(
                    this@ListaFilmesActivity,
                    "Falha na requisição",
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
        val adapter = ListaFilmesAdapter(this, filmes)

        adapter.onClickListener = onClickListener

        activity_lista_filmes_gridview.adapter = adapter

    }

    private fun chamaDetalhesFilme(filme: Filme) {
        var intent = Intent(this@ListaFilmesActivity, DetalhesFilmeActivity::class.java)
        intent.putExtra("filme", filme)
        startActivity(intent)
    }
}
