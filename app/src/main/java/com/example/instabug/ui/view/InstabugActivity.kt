package com.example.instabug.ui.view

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instabug.data.local.DatabaseHelper
import com.example.instabug.R
import com.example.instabug.data.DataManager
import com.example.instabug.data.local.LocalDataManager
import com.example.instabug.data.models.Word
import com.example.instabug.data.remote.InstabugAPIs
import com.example.instabug.data.remote.RemoteDataManager
import com.example.instabug.ui.ViewModelFactory
import com.example.instabug.ui.adapter.WordsAdapter
import com.example.instabug.ui.viewmodel.InstabugViewModel
import com.example.instabug.utils.isNetworkConnected
import kotlinx.android.synthetic.main.activity_instabug.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.URL

class InstabugActivity : AppCompatActivity() {
    lateinit var viewModel: InstabugViewModel
    lateinit var wordsAdapter: WordsAdapter
    lateinit var databaseHelper: DatabaseHelper

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instabug)
        viewModelSetUp()
        setup()
        dataObserving()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun setup() {

        viewModel.getInstabugWords("https://instabug.com/")


        wordsAdapter = WordsAdapter()
        databaseHelper = DatabaseHelper(this)
        wordsRV.apply {
            layoutManager = LinearLayoutManager(this@InstabugActivity)
            adapter = wordsAdapter
        }
    }

    fun viewModelSetUp(){
        viewModel = ViewModelProviders.of(this, ViewModelFactory(DataManager(LocalDataManager(
            DatabaseHelper(this)
        ), RemoteDataManager(InstabugAPIs())
        ))).get(InstabugViewModel::class.java)
    }

    fun dataObserving(){
        viewModel.dataList.observe(this, Observer {
            wordsAdapter.setData(it.toMutableList())
            wordsAdapter.notifyDataSetChanged()
            loaderV.visibility = View.GONE
        })
    }
}