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
import java.net.URL

class InstabugRepository(
    private val dataManager: DataManager
) {

    fun getInstabugWords(url: String, callback: (ApiResponse<String>) -> Unit) {
        Thread(Runnable() {
            try {
                run() {
                    val repoListJsonStr = dataManager.remoteDataManager.getInstabugWords(url)
                    val doc: Document = Jsoup.parse(repoListJsonStr, "UTF-8")
                    var doContent = doc.body().text()
                    callback.invoke(ApiResponse.Success(doContent))
                }
            } catch (e: Exception) {

            }
        }).start()


    }

    fun readCache() = dataManager.localDataManager.readCache()
    fun cachingData(data: List<Word>) = dataManager.localDataManager.cachingData(data)

}