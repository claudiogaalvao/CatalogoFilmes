package br.com.claudiogalvao.catalogofilmes.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.claudiogalvao.catalogofilmes.R
import br.com.claudiogalvao.catalogofilmes.model.Filme
import br.com.claudiogalvao.catalogofilmes.model.RetornoRequisicao
import br.com.claudiogalvao.catalogofilmes.retrofit.RetrofitInitializer
import br.com.claudiogalvao.catalogofilmes.ui.adapter.ListaFilmesAdapter
import kotlinx.android.synthetic.main.activity_lista_filmes.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaFilmesActivity : AppCompatActivity() {
    var filmes: ArrayList<Filme> = arrayListOf()

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
        activity_lista_filmes_gridview.adapter = adapter
    }
}
