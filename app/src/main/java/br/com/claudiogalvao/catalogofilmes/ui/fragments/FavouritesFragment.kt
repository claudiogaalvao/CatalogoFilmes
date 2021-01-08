package br.com.claudiogalvao.catalogofilmes.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.claudiogalvao.catalogofilmes.R
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        populaListaDeFilmes()
        configuraLista()
    }

    private fun populaListaDeFilmes() {
        for(i in 0 until 3) {
            val filme = Filme(i, "Teste", "Skylines", "en", "2020-12-18",
                75.818, 28, 6.7, false, false, emptyArray(),
                "Quando um vírus ameaça transformar os híbridos alienígenas amigáveis que agora vivem na Terra contra os humanos, " +
                        "a capitã Rose Corley deve liderar uma equipe de mercenários de elite em uma missão ao mundo alienígena para salvar o " +
                        "que resta da humanidade.", "/3ombg55JQiIpoPnXYb2oYdr6DtP.jpg", "/2W4ZvACURDyhiNnSIaFPHfNbny3.jpg");
            Log.i("filme", filme.getCapa());
            filmes.add(i, filme);
            Log.i("filme", i.toString())
        }
    }

    private fun configuraLista() {
        Log.i("filme", "Configurando lista...")
        Log.i("filme", filmes.size.toString())
        val adapter = ListaFilmesAdapter(this.requireContext(), filmes)

        adapter.onClickListener = onClickListener

        activity_filmes_favoritos_gridview.adapter = adapter
        Log.i("filme", "Lista configurada")
    }

}
