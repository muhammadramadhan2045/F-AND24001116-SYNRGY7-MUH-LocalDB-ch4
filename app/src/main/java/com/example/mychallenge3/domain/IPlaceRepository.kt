package com.example.mychallenge3.domain

import com.example.mychallenge3.data.model.Place

interface IPlaceRepository {
    fun getPlaces() : ArrayList<Place>

    suspend fun getPlaceByName(placeName: String): Place?

    suspend fun insertPlace(place: Place)

    suspend fun deletePlace(place: Place)

    suspend fun getAllPlacesLocal(): List<Place>

}