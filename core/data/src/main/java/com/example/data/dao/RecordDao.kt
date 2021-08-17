package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.LocalRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {

    @Query("select * from records order by id desc limit 20")
    fun getAll(): Flow<List<LocalRecord>>

    @Query("SELECT * FROM records WHERE reset=:reset")
    fun getAllNoReset(reset: Boolean = false): Flow<List<LocalRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg records: LocalRecord)

}