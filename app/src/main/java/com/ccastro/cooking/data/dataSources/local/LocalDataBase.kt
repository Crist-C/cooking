package com.ccastro.cooking.data.dataSources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ccastro.cooking.data.dataSources.local.daos.RecetaDAO
import com.ccastro.cooking.data.mappers.IngredientesListMapper
import com.ccastro.cooking.data.mappers.LocationMapper
import com.ccastro.cooking.data.mappers.StringListMapper
import com.ccastro.cooking.data.models.entities.RecetaDBEntity

@Database(entities = [RecetaDBEntity::class], version = 8)
@TypeConverters(StringListMapper::class, IngredientesListMapper::class, LocationMapper::class)
abstract class LocalDataBase: RoomDatabase() {
    abstract fun recetasDAO(): RecetaDAO

}