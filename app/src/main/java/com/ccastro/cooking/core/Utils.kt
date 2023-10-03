package com.ccastro.cooking.core

import com.ccastro.cooking.core.Constants.gson
import com.ccastro.cooking.domain.models.Receta
import com.google.gson.reflect.TypeToken

object Utils {

    fun parceRecetaJsonToReceta(recetaJson: String) : Receta {
        return gson.fromJson(recetaJson, object : TypeToken<Receta>(){}.type)
    }
}