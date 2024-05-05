package com.example.mychallenge3.view.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge3.data.model.Place
import com.example.mychallenge3.data.repository.PlaceRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: PlaceRepository) : ViewModel() {


    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    var title: String? = null

    fun getUrlGoogle(title: String): String {
        return "https://www.google.com/search?q=$title"
    }


    private val _insertPlace = MutableLiveData<Boolean>()
    val insertPlace: LiveData<Boolean> = _insertPlace

    fun savePlaceToFavorite(
        name: String,
        description: String,
        photo: String,
        id: Int = -1,
    ) {
        viewModelScope.launch {
            try {
                val place = Place(
                    image = photo,
                    name = name,
                    description = description,

                )

                repository.insertPlace(place)
                _insertPlace.value = true
                Log.d("DetailViewModel", "savePlaceToFavorite: ${place.name}")
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
        }
    }

    private val _deletePlace = MutableLiveData<Boolean>()
    val deletePlace: LiveData<Boolean> = _deletePlace

    fun deletePlaceFromFavorite(place: Place) {
        viewModelScope.launch {
            try {
                repository.deletePlace(place)
                _deletePlace.value = true
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
        }
    }

    private val _placeLocal = MutableLiveData<Place?>()
    val placeLocal: LiveData<Place?> = _placeLocal
    fun getPlaceFromFavorite(name: String) {
        viewModelScope.launch {
            try {
                _placeLocal.value = repository.getPlaceByName(name)
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
        }
    }
}