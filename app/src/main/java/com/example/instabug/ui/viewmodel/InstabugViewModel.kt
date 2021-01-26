package com.example.instabug.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instabug.R
import com.example.instabug.data.models.ApiResponse
import com.example.instabug.data.models.Word
import com.example.instabug.data.repository.InstabugRepository

class InstabugViewModel(private val instabugRepository: InstabugRepository) : ViewModel() {

    var dataList = MutableLiveData<List<Word>>()
    var error = MutableLiveData<String>()
    var cachingError = MutableLiveData<String>()

    fun getInstabugWords() {
        instabugRepository.getInstabugWords({
            when (it) {
                is ApiResponse.Success -> {
                    if (it.body.isNotEmpty()) {
                        var wordsList = it.body.trim()
                            .replace(".", "")
                            .replace(",", "")
                            .replace("\"", "")
                            .replace("/", "")
                            .replace(";", "")
                            .replace("&", "")
                            .splitToSequence(' ')
                            .filter { it.isNotEmpty() }
                            .groupingBy { it }
                            .eachCount()
                        var mappedWordsWithCount = mutableListOf<Word>()
                        for (item in wordsList) {
                            mappedWordsWithCount.add(
                                Word(
                                    item.key,
                                    item.value
                                )
                            )
                        }
                        instabugRepository.cachingData(mappedWordsWithCount)
                        dataList.postValue(mappedWordsWithCount)
                    }
                }
                is ApiResponse.Error -> {
                    error.postValue("something went wrong")
                }
            }
        })
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