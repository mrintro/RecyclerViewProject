package com.example.recyclerviewproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewproject.network.Resource
import com.example.recyclerviewproject.repository.DataRepository
import com.example.recyclerviewproject.response.userData
import kotlinx.coroutines.launch

class DataViewModel(
    private val repository: DataRepository
) : ViewModel() {

    private val _incomingData : MutableLiveData<Resource<userData>> = MutableLiveData()

    val incomingData : LiveData<Resource<userData>>
    get() = _incomingData

    fun getDataFromApi() = viewModelScope.launch {
        _incomingData.value = repository.getDataFromApi()
    }

}