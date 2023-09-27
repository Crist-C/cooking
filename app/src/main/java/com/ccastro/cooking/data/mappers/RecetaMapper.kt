package com.ccastro.cooking.data.mappers

import com.ccastro.cooking.data.models.RecetaDTO
import com.ccastro.cooking.domain.models.Receta

object RecetaMapper {

    fun mapRecetaToRecetaDTO(receta: Receta) : RecetaDTO {
        return RecetaDTO(
            id = receta.id,
            nombre_receta = receta.nombre,
            imagenes = receta.imagenes,
            localizacion = receta.location,
            ingredientes = receta.ingredientes,
            procedimiento = receta.preparacion
        )
    }

    fun mapRecetaDTOToReceta(recetaDTO: RecetaDTO) : Receta {
        return Receta(
            id = recetaDTO.id,
            nombre = recetaDTO.nombre_receta,
            imagenes = recetaDTO.imagenes,
            location = recetaDTO.localizacion,
            ingredientes = recetaDTO.ingredientes,
            preparacion = recetaDTO.procedimiento
        )
    }
}