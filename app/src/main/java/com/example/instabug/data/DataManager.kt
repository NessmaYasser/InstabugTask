package com.example.instabug.data

import com.example.instabug.data.local.LocalDataManager
import com.example.instabug.data.remote.RemoteDataManager

class DataManager(val localDataManager: LocalDataManager,val remoteDataManager: RemoteDataManager) {
}