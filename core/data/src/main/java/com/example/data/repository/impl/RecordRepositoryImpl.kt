package com.example.data.repository.impl

import com.example.data.dao.RecordDao
import com.example.data.model.LocalRecord
import com.example.data.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class RecordRepositoryImpl(private val recordDao: RecordDao): RecordRepository {

    override suspend fun addRecord(record: LocalRecord) {
        recordDao.insertAll(record)
    }

    override suspend fun markRecordsAsReset(records: List<LocalRecord>) {
        recordDao.insertAll(*records.toTypedArray())
    }

    override fun getCurrentRecords(): Flow<List<LocalRecord>> {
        return recordDao.getAllNoReset()
    }

    override fun getAllRecords(): Flow<List<LocalRecord>> {
        return recordDao.getAll()
    }
}