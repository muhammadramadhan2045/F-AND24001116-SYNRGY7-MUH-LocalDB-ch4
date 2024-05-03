package com.example.mychallenge3.data.di

import android.content.Context
import com.example.mychallenge3.data.repository.PlaceRepository
import com.example.mychallenge3.data.source.remote.PlaceRemoteDataSourceImpl

object Injection {
    fun provideRepository(context: Context): PlaceRepository {
        val remoteDataSource = PlaceRemoteDataSourceImpl(context)
        return PlaceRepository(remoteDataSource)
    }
}