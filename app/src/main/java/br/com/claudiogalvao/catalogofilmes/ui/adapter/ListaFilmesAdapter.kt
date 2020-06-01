package br.com.claudiogalvao.catalogofilmes.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import br.com.claudiogalvao.catalogofilmes.R
import br.com.claudiogalvao.catalogofilmes.model.Filme
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.filme_item.view.*

interface OnClickListener {

    fun onClick(filme: Filme)
}

class ListaFilmesAdapter(val context: Context, val filmes: ArrayList<Filme>) : BaseAdapter() {
    var onClickListener: OnClickListener? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var filme = filmes[position]

        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val filmeView = inflator.inflate(R.layout.filme_item, null)

        Picasso.get().load(filme.getCapa()).into(filmeView.filme_item_imageview)
        filmeView.filme_item_imageview.setOnClickListener { onClickListener?.onClick(filme) }

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