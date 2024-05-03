package com.example.mychallenge3.data.repository

import com.example.mychallenge3.data.model.Place
import com.example.mychallenge3.data.source.PlaceRemoteDataSource
import com.example.mychallenge3.domain.IPlaceRepository

class PlaceRepository (
    private val remoteDataSource: PlaceRemoteDataSource
) :IPlaceRepository{
    override fun getPlaces(): ArrayList<Place> {
        return remoteDataSource.getPlaces()
    }
}