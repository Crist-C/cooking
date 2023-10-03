package com.ccastro.cooking.data.dataSources.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ccastro.cooking.data.models.entities.RecetaDBEntity
import com.ccastro.cooking.domain.models.Ingredientes
import com.ccastro.cooking.domain.models.Location
import kotlinx.coroutines.flow.Flow

@Dao
interface RecetaDAO {

    // Create - Update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(receta: RecetaDBEntity)

    @Query("SELECT count(idReceta) FROM recetas")
    suspend fun countRecetas(): Int

    @Query("SELECT * FROM recetas")
    fun getAll(): Flow<List<RecetaDBEntity>>

    @Query("SELECT * FROM recetas WHERE idReceta = :id")
    suspend fun getById(id: Int): RecetaDBEntity

    @Query("UPDATE recetas SET favorito = :favorito WHERE idReceta = :id")
    suspend fun updateFavorito(id: Int, favorito: Boolean)

    @Query("UPDATE recetas SET nombreReceta = :nuevoNombre, imagenes = :nuevasImagenes, localizacion = :nuevaLocalizacion, ingredientes = :nuevosIngredientes, procedimiento = :nuevoProcedimiento, resumen = :nuevoResumen WHERE idReceta = :idReceta")
    fun updateRecetaExceptFavorito(idReceta: Int, nuevoNombre: String, nuevasImagenes: List<String>, nuevaLocalizacion: Location, nuevosIngredientes: List<Ingredientes>, nuevoProcedimiento: String, nuevoResumen: String)
}