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

        val call = RetrofitInitializer().filmeService().buscaFilmesPopulares()
        call.enqueue(object : Callback<RetornoRequisicao> {
            override fun onFailure(call: Call<RetornoRequisicao>, t: Throwable) {
                Toast.makeText(this@ListaFilmesActivity,
                    "Falha na requisição",
                    Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<RetornoRequisicao>, response: Response<RetornoRequisicao>) {
                response?.body()?.let {
                    var listaFilmes: ArrayList<Filme> = it.getResults()
                    for(filme in listaFilmes) {
                        filmes.add(filme)
                    }
                    Toast.makeText(this@ListaFilmesActivity,
                        "${filmes[0].toString()} ",
                        Toast.LENGTH_LONG).show()
                }
            }

        })

//        val adapter = ListaFilmesAdapter(this, filmes)
//        activity_lista_filmes_gridview.adapter = adapter

    }

    fun filmesExemplo() {
//        filmes.add(Filme("The Nun", "https://br.web.img3.acsta.net/pictures/18/07/18/21/53/1348208.jpg"))
//        filmes.add(Filme("Capitã Marvel", "https://br.web.img2.acsta.net/pictures/19/02/04/18/35/1468867.jpg"))
//        filmes.add(Filme("Star Wars", "https://upload.wikimedia.org/wikipedia/pt/a/ae/Starwars_06.jpg"))
//        filmes.add(Filme("Get Out", "https://i.pinimg.com/originals/66/20/90/66209037dff62f122f2f6050e9ed2815.jpg"))
//        filmes.add(Filme("Avengers: End Game", "https://conteudo.imguol.com.br/c/entretenimento/90/2019/04/23/poster-brasileiro-de-vingadores-ultimato-1556045801213_v2_1225x1800.jpg"))
    }
}
