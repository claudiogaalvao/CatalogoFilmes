package br.com.claudiogalvao.catalogofilmes.ui.activity

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.MenuItemCompat
import br.com.claudiogalvao.catalogofilmes.R
import br.com.claudiogalvao.catalogofilmes.domain.model.Filme
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes_filme.*

class DetalhesFilmeActivity : AppCompatActivity() {

    private lateinit var filme: Filme;
    private lateinit var favoriteItemMenu: MenuItem;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_filme)

        filme = intent.getSerializableExtra("filme") as Filme

        Picasso.get().load(filme?.getCapa()).into(activity_detalhes_filme_capa)
        activity_detalhes_filme_titulo.text = filme?.getTitulo()
        activity_detalhes_filme_lancamento.text = filme?.getDataDeLancamento()
        activity_detalhes_filme_avaliacao.text = filme?.getAvaliacao()
        activity_detalhes_filme_sinopse.text = filme?.getSinopse()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.details_movie_options_menu, menu)
        favoriteItemMenu = menu?.findItem(R.id.ic_addFavorite)!!
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.ic_addFavorite -> {
                changeIconFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun changeIconFavorite() {
        if(favoriteItemMenu.icon.constantState!!.equals(resources.getDrawable(R.drawable.ic_favorite).constantState)) {
            filme.setIsFavorite(false);
            Toast.makeText(this, "Removido dos favoritos", Toast.LENGTH_SHORT).show()
            favoriteItemMenu.setIcon(R.drawable.ic_favorite_border)
            return;
        }

        filme.setIsFavorite(true);
        Toast.makeText(this, "Adicionado aos favoritos", Toast.LENGTH_SHORT).show()
        favoriteItemMenu.setIcon(R.drawable.ic_favorite)
    }
}
