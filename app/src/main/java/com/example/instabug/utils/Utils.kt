package com.example.instabug.utils

import com.example.instabug.data.models.Word

fun convertStringToList(word : String) : List<Word>{
    var result = mutableListOf<Word>()
    var wordsList = word.trim()
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
    for (item in wordsList) {
        result.add(
            Word(
                item.key,
                item.value
            )
        )
    }
    return result
}