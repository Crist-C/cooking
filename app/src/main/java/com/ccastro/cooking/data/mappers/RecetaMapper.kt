package com.ccastro.cooking.data.mappers

import com.ccastro.cooking.data.models.dto.RecetaApiDTO
import com.ccastro.cooking.data.models.entities.RecetaDBEntity
import com.ccastro.cooking.domain.models.Receta

object RecetaMapper {

    fun mapRecetaToRecetaDTO(receta: Receta) : RecetaApiDTO {
        return RecetaApiDTO(
            id = receta.id,
            nombreReceta = receta.nombre,
            imagenes = receta.imagenes,
            localizacion = receta.location,
            ingredientes = receta.ingredientes,
            procedimiento = receta.preparacion,
            descripcion = receta.descripcion
        )
    }

    fun mapRecetaApiDTOToReceta(recetaApiDTO: RecetaApiDTO) : Receta {
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

    fun mapListRecetaApiDTOToListReceta(listRecetaApiDTO: List<RecetaApiDTO>) : List<Receta> {

        val listaRetorno: MutableList<Receta> = mutableListOf()
        listRecetaApiDTO.map { listaRetorno.add( mapRecetaApiDTOToReceta(it)) }
        return listaRetorno

    }

    fun mapRecetaToRecetaDBEntity(receta: Receta): RecetaDBEntity {
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

    fun mapRecetaDBEntityToReceta(recetaDBEntity: RecetaDBEntity): Receta {
        return Receta(
            id = recetaDBEntity.idReceta,
            nombre = recetaDBEntity.nombreReceta,
            imagenes = recetaDBEntity.imagenes,
            location = recetaDBEntity.localizacion,
            ingredientes = recetaDBEntity.ingredientes,
            preparacion = recetaDBEntity.procedimiento,
            descripcion = recetaDBEntity.resumen
        )
    }

    fun mapListRecetaDBEntityToListReceta(listRecetaDBEntity: List<RecetaDBEntity>) : List<Receta> {

        val listaRetorno: MutableList<Receta> = mutableListOf()
        listRecetaDBEntity.map { listaRetorno.add( mapRecetaDBEntityToReceta(it)) }
        return listaRetorno

    }

}