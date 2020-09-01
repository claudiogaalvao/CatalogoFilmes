package br.com.claudiogalvao.catalogofilmes.data.local.bd.dao

import androidx.room.*
import br.com.claudiogalvao.catalogofilmes.data.local.bd.model.FilmeBD

@Dao
interface FilmeDAO {
    @Query("SELECT * FROM filmes")
    fun getAll(): List<FilmeBD>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(filmes: List<FilmeBD>)

    @Update
    fun update(filme: FilmeBD)

    @Delete
    fun delete(filme: FilmeBD)
}