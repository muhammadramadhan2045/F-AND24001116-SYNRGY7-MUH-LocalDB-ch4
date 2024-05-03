package com.example.mychallenge3.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychallenge3.data.model.Place
import com.example.mychallenge3.data.repository.PlaceRepository

class HomeViewModel(private val repository: PlaceRepository) : ViewModel() {

    private val _places: MutableLiveData<ArrayList<Place>> = MutableLiveData()
    val places: LiveData<ArrayList<Place>>
        get() = _places

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading



    init {
        getPlaces()

    }
    fun getPlaces() {
        _loading.value = true
        _places.value = repository.getPlaces()
        _loading.value = false
    }
}