package br.com.claudiogalvao.catalogofilmes.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import br.com.claudiogalvao.catalogofilmes.R
import br.com.claudiogalvao.catalogofilmes.model.Filme
import br.com.claudiogalvao.catalogofilmes.ui.adapter.ListaFilmesAdapter
import kotlinx.android.synthetic.main.activity_lista_filmes.*

class ListaFilmesActivity : AppCompatActivity() {
    var filmes: ArrayList<Filme> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)

        filmesExemplo()

        val adapter = ListaFilmesAdapter(this, filmes)
        lista_filmes_gridview.adapter = adapter
    }

    fun filmesExemplo() {
        filmes.add(Filme("The Nun"))
        filmes.add(Filme("Avengers: Infinity War"))
        filmes.add(Filme("Star Wars"))
        filmes.add(Filme("Get Out"))
        filmes.add(Filme("Avengers: End Game"))
    }
}
