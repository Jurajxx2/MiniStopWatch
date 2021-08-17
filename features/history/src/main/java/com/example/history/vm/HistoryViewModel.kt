package com.example.history.vm

import android.content.Context
import android.util.Log
import androidx.lifecycle.liveData
import com.example.base.BaseViewModel
import com.example.data.AppDatabase
import com.example.data.repository.RecordRepository
import com.example.data.repository.impl.RecordRepositoryImpl
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class HistoryViewModel: BaseViewModel() {

    private lateinit var appDatabase: AppDatabase
    private lateinit var repository: RecordRepository

    fun initDatabase(context: Context) {
        appDatabase = AppDatabase.getInstance(context)
        repository = RecordRepositoryImpl(appDatabase.recordDao())
    }

    val values = liveData {
        repository.getAllRecords().collect {
            emit(it)
        }
    }
}