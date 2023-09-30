package com.ccastro.cooking.domain.models

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("urlFlagIcon") val urlImgBandera: String,
    @SerializedName("country") val pais: String,
    @SerializedName("latitud") val lat: Float,
    @SerializedName("longitud") val long: Float,
    @SerializedName("region") val regionName: String,
)
