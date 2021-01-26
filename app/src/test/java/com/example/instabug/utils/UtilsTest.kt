package com.example.instabug.utils

import com.example.instabug.data.models.Word
import org.junit.Assert.*
import org.junit.Test

class UtilsTest {
    @Test
    fun getListFromString() {
        var wordString = "test convert test"
        var ressult = convertStringToList(wordString)
        assertEquals(listOf(Word("test", 2), Word("convert", 1)), ressult)
    }

    @Test
    fun getEmptyListIfStringEmpty(){
        var wordString = " "
        var ressult = convertStringToList(wordString)
        assertEquals(listOf<Word>(), ressult)
    }
}