package com.example.data.repository

import com.example.data.model.LocalRecord
import kotlinx.coroutines.flow.Flow

interface RecordRepository {
    suspend fun addRecord(record: LocalRecord)
    suspend fun markRecordsAsReset(records: List<LocalRecord>)
    fun getCurrentRecords(): Flow<List<LocalRecord>>
    fun getAllRecords(): Flow<List<LocalRecord>>
}