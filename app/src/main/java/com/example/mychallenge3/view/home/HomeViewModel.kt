package com.example.mychallenge3.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge3.data.model.Place
import com.example.mychallenge3.data.repository.PlaceRepository
import com.example.mychallenge3.data.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PlaceRepository,private val userRepository: UserRepository) : ViewModel() {

    private val _places: MutableLiveData<ArrayList<Place>> = MutableLiveData()
    val places: LiveData<ArrayList<Place>>
        get() = _places

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading



    init {
        getPlaces()

    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
        }
    }
    fun getPlaces() {
        _loading.value = true
        _places.value = repository.getPlaces()
        _loading.value = false
    }
}