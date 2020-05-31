package br.com.claudiogalvao.catalogofilmes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.claudiogalvao.catalogofilmes.R
import br.com.claudiogalvao.catalogofilmes.model.Filme
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.filme_item.view.*

class ListaFilmesAdapter(val context: Context, val filmes: ArrayList<Filme>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var filme = filmes[position]

        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val filmeView = inflator.inflate(R.layout.filme_item, null)

        Picasso.get().load(filme.capaUrl).into(filmeView.filme_item_imageview)

        return filmeView
    }

    override fun getItem(position: Int): Any {
        return filmes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return filmes.size
    }
}