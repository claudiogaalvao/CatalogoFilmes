package br.com.claudiogalvao.catalogofilmes.data.local.bd.config

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.claudiogalvao.catalogofilmes.data.local.bd.dao.FilmeDAO
import br.com.claudiogalvao.catalogofilmes.data.local.bd.model.FilmeBD

@Database(entities = arrayOf(FilmeBD::class), version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun filmeDAO(): FilmeDAO
}