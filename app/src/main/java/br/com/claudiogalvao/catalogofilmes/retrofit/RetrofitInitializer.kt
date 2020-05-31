package br.com.claudiogalvao.catalogofilmes.retrofit

import br.com.claudiogalvao.catalogofilmes.retrofit.service.FilmeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun filmeService() : FilmeService = retrofit.create(FilmeService::class.java)

}