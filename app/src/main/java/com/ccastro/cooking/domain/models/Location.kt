package com.ccastro.cooking.domain.models

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("urlFlagIcon") val urlImgBandera: String,
    @SerializedName("country") val pais: String,
    @SerializedName("latitud") var lat: Double,
    @SerializedName("longitud") var long: Double,
    @SerializedName("region") val regionName: String,
) {
    fun isValid(): Boolean {
        return isValidUrl(urlImgBandera) && isPaisValid() && isRegionValid() &&
                isLatitudValid() && isLongitudValid()
    }

    private fun isValidUrl(url: String): Boolean {
        return url.startsWith("https://")
    }

    private fun isPaisValid(): Boolean {
        return pais.isNotBlank()
    }

    private fun isRegionValid(): Boolean {
        return regionName.isNotBlank()
    }

    private fun isLatitudValid(): Boolean {
        return lat in -90.0..90.0
    }

    private fun isLongitudValid(): Boolean {
        return long in -180.0..180.0
    }
}
