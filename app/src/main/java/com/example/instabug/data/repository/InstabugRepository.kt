package com.example.instabug.data.repository

import android.view.View
import com.example.instabug.data.DataManager
import com.example.instabug.data.local.LocalDataManager
import com.example.instabug.data.models.ApiResponse
import com.example.instabug.data.models.Word
import com.example.instabug.data.remote.RemoteDataManager
import kotlinx.android.synthetic.main.activity_instabug.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class InstabugRepository(val dataManager: DataManager) {

    fun getInstabugWords(callback: (ApiResponse<String>) -> Unit) {
        Thread(Runnable() {
            try {
                run() {
                    val repoListJsonStr = dataManager.remoteDataManager.getInstabugWords()
                    val doc: Document = Jsoup.parse(repoListJsonStr, "UTF-8")
                    var doContent = doc.body().text()
                    callback.invoke(ApiResponse.Success(doContent))


                }
            } catch (e: Exception) {
                callback.invoke(ApiResponse.Error(e.message))
            }
        }).start()


    }

    fun readCache(callback: (ApiResponse<List<Word>>) -> Unit) {
        Thread(Runnable() {
            run() {
                try {
                    var data = dataManager.localDataManager.readCache()
                    callback.invoke(ApiResponse.Success(data))
                } catch (e: Exception) {
                    callback.invoke(ApiResponse.Error(e.message))
                }
            }
        }).start()
    }

    fun cachingData(data: List<Word>) {
        Thread(Runnable() {
            run() {
                dataManager.localDataManager.cachingData(data)
            }
        }).start()
    }

}