package com.example.mychallenge3.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mychallenge3.data.source.local.entity.PlaceEntity

@Dao
interface PlaceDao {

    @Query("SELECT * FROM place")
    suspend fun getPlaces(): List<PlaceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(place: PlaceEntity)
    @Delete
    suspend fun deletePlace(place: PlaceEntity)

    @Query("SELECT * FROM place WHERE name = :placeName")
    suspend fun getPlaceByName(placeName: String): PlaceEntity?


}