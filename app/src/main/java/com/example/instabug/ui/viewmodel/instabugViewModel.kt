package com.example.instabug.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instabug.data.models.ApiResponse
import com.example.instabug.data.models.Word
import com.example.instabug.data.repository.InstabugRepository

class InstabugViewModel(private val instabugRepository: InstabugRepository) : ViewModel(){

    var dataList  = MutableLiveData<List<Word>>()

    fun getInstabugWords(url : String){
        instabugRepository.getInstabugWords(url, {
            when(it){
                is ApiResponse.Success -> {
                    if(it.body.isNotEmpty()) {
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
                        dataList.postValue(mappedWordsWithCount)
                    }
                }
            }
        })
    }

}