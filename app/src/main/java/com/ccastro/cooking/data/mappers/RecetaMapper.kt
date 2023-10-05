package com.ccastro.cooking.data.mappers

import com.ccastro.cooking.core.Constants
import com.ccastro.cooking.data.models.dto.RecetaApiDTO
import com.ccastro.cooking.data.models.entities.RecetaDBEntity
import com.ccastro.cooking.domain.models.Receta
import com.google.gson.reflect.TypeToken

object RecetaMapper {

    fun parceRecetaJsonToReceta(recetaJson: String) : Receta {
        return Constants.gson.fromJson(recetaJson, object : TypeToken<Receta>(){}.type)
    }

    fun mapRecetaApiDtoToReceta(recetaApiDTO: RecetaApiDTO) : Receta {
        return Receta(
            id = recetaApiDTO.id,
            nombre = recetaApiDTO.nombreReceta,
            imagenes = recetaApiDTO.imagenes,
            location = recetaApiDTO.localizacion,
            ingredientes = recetaApiDTO.ingredientes,
            preparacion = recetaApiDTO.procedimiento,
            descripcion = recetaApiDTO.descripcion
        )
    }

    fun mapRecetaToRecetaDbEntity(receta: Receta): RecetaDBEntity {
        return RecetaDBEntity(
            idReceta = receta.id,
            nombreReceta = receta.nombre,
            imagenes = receta.imagenes,
            localizacion = receta.location,
            ingredientes = receta.ingredientes,
            procedimiento = receta.preparacion,
            resumen = receta.descripcion,
            favorito = receta.favorito
        )
    }

    fun mapRecetaDbEntityToReceta(recetaDBEntity: RecetaDBEntity): Receta {
        return Receta(
            id = recetaDBEntity.idReceta,
            nombre = recetaDBEntity.nombreReceta,
            imagenes = recetaDBEntity.imagenes,
            location = recetaDBEntity.localizacion,
            ingredientes = recetaDBEntity.ingredientes,
            preparacion = recetaDBEntity.procedimiento,
            descripcion = recetaDBEntity.resumen,
            favorito = recetaDBEntity.favorito
        )
    }

}