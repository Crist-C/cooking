package com.ccastro.cooking.data.dataSources.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ccastro.cooking.data.models.entities.RecetaDBEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecetaDAO {

    // Create - Update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(receta: RecetaDBEntity)

    @Query("SELECT * FROM recetas WHERE idReceta = :id")
    suspend fun read(id: Int): RecetaDBEntity

    @Query("SELECT * FROM recetas")
    fun getAll(): Flow<List<RecetaDBEntity>>?
}