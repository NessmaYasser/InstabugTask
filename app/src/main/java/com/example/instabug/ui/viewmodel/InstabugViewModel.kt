package com.example.instabug.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instabug.R
import com.example.instabug.data.models.ApiResponse
import com.example.instabug.data.models.Word
import com.example.instabug.data.repository.InstabugRepository
import com.example.instabug.utils.convertStringToList

class InstabugViewModel(val instabugRepository: InstabugRepository) : ViewModel() {

    var dataList = MutableLiveData<List<Word>>()
    var error = MutableLiveData<String>()
    var cachingError = MutableLiveData<String>()

    fun getInstabugWords() {
        try {
            instabugRepository.getInstabugWords({
                when (it) {
                    is ApiResponse.Success -> {
                        if (it.body.isNotEmpty()) {
                            var wordsList = convertStringToList(it.body)
                            instabugRepository.cachingData(wordsList)
                            dataList.postValue(wordsList)
                        }
                    }
                    is ApiResponse.Error -> {
                        error.postValue(it.errorMessage)
                    }
                }
            })
        }catch (e : Exception){
            error.postValue(e.message)

        }
    }

    fun getCache() {
        instabugRepository.readCache {
            when (it) {
                is ApiResponse.Success -> {
                    dataList.postValue(it.body)
                }
                is ApiResponse.Error -> {
                    cachingError.postValue(it.errorMessage)
                }
            }
        }
    }

}