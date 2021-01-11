package br.com.claudiogalvao.catalogofilmes.data.local.bd.dao

import androidx.room.*
import br.com.claudiogalvao.catalogofilmes.data.local.bd.model.FilmeBD

@Dao
interface FilmeDAO {
    @Query("SELECT * FROM filmes")
    fun getAll(): List<FilmeBD>

    @Query("SELECT * from filmes where isFavorite = 1")
    fun getFavoriteMovies(): List<FilmeBD>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(filmes: List<FilmeBD>)

    @Query("UPDATE filmes SET isFavorite = :isFavorite WHERE id = :filmeId")
    fun setIsFavorite(isFavorite: Boolean, filmeId: Int)

    @Update
    fun update(filme: FilmeBD)

    @Delete
    fun delete(filme: FilmeBD)
}