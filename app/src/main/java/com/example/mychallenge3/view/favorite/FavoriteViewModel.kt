package com.example.mychallenge3.view.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge3.data.model.Place
import com.example.mychallenge3.data.repository.PlaceRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: PlaceRepository) : ViewModel() {
    private val _places = MutableLiveData<List<Place>>()
    val places: LiveData<List<Place>> = _places

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading


    fun getPlacesFromLocal() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _places.value = repository.getAllPlacesLocal()
                _loading.value = false
            } catch (throwable: Throwable) {
                _loading.value = false
                _error.value = throwable
            }
        }
    }
}