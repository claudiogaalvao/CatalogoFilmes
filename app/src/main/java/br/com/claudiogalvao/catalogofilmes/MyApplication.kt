package br.com.claudiogalvao.catalogofilmes

import android.app.Application
import androidx.room.Room
import br.com.claudiogalvao.catalogofilmes.data.local.bd.config.AppDataBase
import br.com.claudiogalvao.catalogofilmes.data.local.datasource.LocalDatasource
import br.com.claudiogalvao.catalogofilmes.data.remote.datasource.RemoteDatasource
import br.com.claudiogalvao.catalogofilmes.di.FilmesModule
import br.com.claudiogalvao.catalogofilmes.domain.repository.FilmesRepository
import br.com.claudiogalvao.catalogofilmes.domain.usecase.ListaFilmesCasoDeUso

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        inicializar()
    }

    private fun inicializar() {
        //Room
        val database = Room.databaseBuilder(this, AppDataBase::class.java, "filmes_db")
            .allowMainThreadQueries().build()

        val localDatasource = LocalDatasource(database.filmeDAO())
        val remoteDatasource = RemoteDatasource(this)
        val repository =
            FilmesRepository(datasourceLocal = localDatasource, datasourceRemoto = remoteDatasource)
        FilmesModule.filmesCasoDeUso = ListaFilmesCasoDeUso(repository)
    }
}