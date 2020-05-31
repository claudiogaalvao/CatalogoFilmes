package br.com.claudiogalvao.catalogofilmes.model

import br.com.claudiogalvao.catalogofilmes.model.Filme

class RetornoRequisicao(private val page: Int,
                        private val total_results: Int,
                        private val total_pages: Int,
                        private val results: ArrayList<Filme>) {

    fun getResults() : ArrayList<Filme> {
        return results;
    }
}