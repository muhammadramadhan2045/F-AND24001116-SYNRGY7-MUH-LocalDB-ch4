package com.example.mychallenge3.domain

import com.example.mychallenge3.data.model.Place

interface IPlaceRepository {
    fun getPlaces() : ArrayList<Place>
}