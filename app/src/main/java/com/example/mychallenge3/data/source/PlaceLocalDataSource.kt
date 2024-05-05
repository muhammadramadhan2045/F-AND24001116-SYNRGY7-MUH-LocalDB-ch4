package com.example.mychallenge3.data.source

import com.example.mychallenge3.data.model.Place
import com.example.mychallenge3.data.source.local.entity.PlaceEntity

interface PlaceLocalDataSource {
    suspend fun getAllPlacesLocal(): List<PlaceEntity>
    suspend fun getPlaceByName(placeName :String): PlaceEntity?
    suspend fun insertPlace(place: PlaceEntity)
    suspend fun deletePlace(place: PlaceEntity)
}