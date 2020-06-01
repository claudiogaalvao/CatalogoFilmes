package br.com.claudiogalvao.catalogofilmes.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.claudiogalvao.catalogofilmes.R
import br.com.claudiogalvao.catalogofilmes.model.Filme
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes_filme.*
import kotlinx.android.synthetic.main.filme_item.view.*
import java.io.Serializable

class DetalhesFilmeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_filme)

        val filme: Filme? = intent.getSerializableExtra("filme") as Filme

        Picasso.get().load(filme?.getCapa()).into(activity_detalhes_filme_capa)
        activity_detalhes_filme_titulo.text = filme?.getTitulo()
        activity_detalhes_filme_lancamento.text = filme?.getDataDeLancamento()
        activity_detalhes_filme_avaliacao.text = filme?.getAvaliacao()
        activity_detalhes_filme_sinopse.text = filme?.getSinopse()
    }
}
