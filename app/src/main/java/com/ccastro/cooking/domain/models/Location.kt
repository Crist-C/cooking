package com.ccastro.cooking.domain.models

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("urlFlagIcon") val urlImgBandera: String,
    @SerializedName("country") val pais: String,
    @SerializedName("latitud") val lat: Double,
    @SerializedName("longitud") val long: Double,
    @SerializedName("region") val regionName: String,
)
