package com.example.mychallenge3.data.source.local

import com.example.mychallenge3.data.model.Place
import com.example.mychallenge3.data.source.PlaceLocalDataSource
import com.example.mychallenge3.data.source.local.entity.PlaceEntity
import com.example.mychallenge3.data.source.local.room.PlaceDao

class PlaceLocalDataSourceImpl(private val placeDao: PlaceDao) : PlaceLocalDataSource {
    override suspend fun getAllPlacesLocal(): List<PlaceEntity> = placeDao.getPlaces()

    override suspend fun getPlaceByName(placeName: String): PlaceEntity? {
        return placeDao.getPlaceByName(placeName)
    }
    override suspend fun insertPlace(place: PlaceEntity) = placeDao.insertPlace(place)
    override suspend fun deletePlace(place: PlaceEntity) = placeDao.deletePlace(place)

}