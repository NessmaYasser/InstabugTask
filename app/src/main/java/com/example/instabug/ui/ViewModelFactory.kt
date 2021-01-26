package com.example.instabug.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.instabug.data.DataManager
import com.example.instabug.data.repository.InstabugRepository
import com.example.instabug.ui.viewmodel.InstabugViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val dataManager: DataManager) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(InstabugViewModel::class.java)){
            return InstabugViewModel(InstabugRepository(dataManager)) as T
        }
        throw IllegalArgumentException("Unknown class!")
    }
}