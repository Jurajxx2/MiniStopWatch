package com.example.home.vm

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.data.AppDatabase
import com.example.data.model.LocalRecord
import com.example.data.repository.RecordRepository
import com.example.data.repository.impl.RecordRepositoryImpl
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel() : BaseViewModel() {

    private lateinit var appDatabase: AppDatabase
    private lateinit var repository: RecordRepository

    fun initDatabase(context: Context) {
        appDatabase = AppDatabase.getInstance(context)
        repository = RecordRepositoryImpl(appDatabase.recordDao())
    }

    val currentTime = MutableLiveData<Long>(0)
    val isRunning = MutableLiveData(false)
    val error = MutableLiveData<String>()

    val values = liveData {
        repository.getCurrentRecords().catch {
            Log.d("WATCHX", "loading values error")
            error.postValue("Chyba pri ziskavani hodnot")
        }.collect {
            Log.d("WATCHX", "loading values success ${it.size}")
            emit(it)
        }
    }

    fun insertValue() {
        viewModelScope.launch {
            try {
                repository.addRecord(LocalRecord(
                    time = currentTime.value,
                    reset = false
                ))
            } catch (e: Exception) {
                Log.d("WATCHX", "Error: ${e.stackTraceToString()}")
            }

        }
    }

    fun resetValues() {
        viewModelScope.launch {
            repository.markRecordsAsReset(values.value?.map { it.copy(reset = true) } ?: emptyList())
        }
    }
}