package com.ccastro.cooking.data.mappers

import androidx.room.TypeConverter
import com.ccastro.cooking.domain.models.Ingrediente
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class IngredientesListMapper {

    // Convertidor para List<Ingredientes>
    @TypeConverter
    fun fromString(value: String): List<Ingrediente> {
        val listType = object : TypeToken<List<Ingrediente>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Ingrediente>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}