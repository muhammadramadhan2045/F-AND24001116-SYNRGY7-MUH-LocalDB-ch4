package com.example.mychallenge3.data.source

import com.example.mychallenge3.data.model.Place

interface PlaceRemoteDataSource {
    fun getPlaces() : ArrayList<Place>
}