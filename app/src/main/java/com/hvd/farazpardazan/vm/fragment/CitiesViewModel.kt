package com.hvd.farazpardazan.vm.fragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hvd.farazpardazan.data.net.model.City

class CitiesViewModel @ViewModelInject constructor(cities: List<City>) : ViewModel() {

    private val _citiesData = MutableLiveData<List<City>>()
    val citiesData: LiveData<List<City>> get() = _citiesData

    private val _selectedCity = MutableLiveData<City>()
    val selectedCity: LiveData<City> get() = _selectedCity

    init {
        _citiesData.value = cities
    }

    public fun changeCity(city: City) {
        _selectedCity.value = city
    }

    fun selectDefaultCity(){
        _selectedCity.value = citiesData.value?.get(0)
    }
}