package com.example.recyclerviewproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerviewproject.repository.BaseRepository
import com.example.recyclerviewproject.repository.DataRepository
import java.lang.IllegalArgumentException

class ViewModelFactory (
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DataViewModel::class.java) -> DataViewModel(repository as DataRepository) as T
            else -> throw IllegalArgumentException("ViewModel Class Not Found")
        }
    }
}