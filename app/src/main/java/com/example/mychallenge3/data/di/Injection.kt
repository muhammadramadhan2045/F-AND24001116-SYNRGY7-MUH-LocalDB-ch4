package com.example.mychallenge3.data.di

import android.content.Context
import com.example.mychallenge3.data.repository.PlaceRepository
import com.example.mychallenge3.data.source.local.PlaceLocalDataSourceImpl
import com.example.mychallenge3.data.source.local.room.PlaceDatabase
import com.example.mychallenge3.data.source.remote.PlaceRemoteDataSourceImpl

object Injection {
    fun provideRepository(context: Context): PlaceRepository {
        val database = PlaceDatabase.getInstance(context)
        val remoteDataSource = PlaceRemoteDataSourceImpl(context)
        val localDataSource = PlaceLocalDataSourceImpl(database.placeDao())
        return PlaceRepository(remoteDataSource, localDataSource)
    }
}