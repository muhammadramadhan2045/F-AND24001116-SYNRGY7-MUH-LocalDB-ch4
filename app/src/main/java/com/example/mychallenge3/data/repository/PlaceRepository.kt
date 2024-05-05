package com.example.mychallenge3.data.repository

import com.example.mychallenge3.data.model.Place
import com.example.mychallenge3.data.source.PlaceLocalDataSource
import com.example.mychallenge3.data.source.PlaceRemoteDataSource
import com.example.mychallenge3.domain.IPlaceRepository
import com.example.mychallenge3.utils.DataMapper

class PlaceRepository (
    private val remoteDataSource: PlaceRemoteDataSource,
    private val localDataSource: PlaceLocalDataSource
) :IPlaceRepository{
    override fun getPlaces(): ArrayList<Place> {
        return remoteDataSource.getPlaces()
    }

    override suspend fun getPlaceByName(placeName: String): Place? {
        return localDataSource.getPlaceByName(placeName)?.let { DataMapper.mapEntityToDomain(it) }
    }


    override suspend fun insertPlace(place: Place) {
        return localDataSource.insertPlace(DataMapper.mapDomainToEntity(place))
    }

    override suspend fun deletePlace(place: Place) {
        return localDataSource.deletePlace(DataMapper.mapDomainToEntity(place))
    }

    override suspend fun getAllPlacesLocal(): List<Place> {
        return DataMapper.mapEntitiesToDomain(localDataSource.getAllPlacesLocal())
    }


}