package com.example.mychallenge3.data.source.remote

import android.content.Context
import com.example.mychallenge3.R
import com.example.mychallenge3.data.model.Place
import com.example.mychallenge3.data.source.PlaceRemoteDataSource

class PlaceRemoteDataSourceImpl(private val context: Context) : PlaceRemoteDataSource {
    override fun getPlaces(): ArrayList<Place> {
        val resources = context.resources
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto =resources.getStringArray(R.array.data_photo)
        val places = ArrayList<Place>()
        dataName.indices.forEach { index ->
            places.add(Place(dataName[index], dataDescription[index], dataPhoto[index]))
        }
        return places
    }

}