package com.example.instabug.data.local

import com.example.instabug.data.models.Word

class LocalDataManager (private val  db : DatabaseHelper) {
    fun readCache()  = db.readCachedData()
    fun cachingData(data : List<Word>) = db.cachingData(data)
}