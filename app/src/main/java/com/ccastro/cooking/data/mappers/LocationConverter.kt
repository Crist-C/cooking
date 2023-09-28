package com.ccastro.cooking.data.mappers

import androidx.room.TypeConverter
import com.ccastro.cooking.domain.models.Location
import com.google.gson.Gson

class LocationConverter {
    @TypeConverter
    fun fromLocation(location: Location): String {
        return Gson().toJson(location)
    }

    @TypeConverter
    fun toLocation(value: String): Location {
        return Gson().fromJson(value, Location::class.java)
    }
}
