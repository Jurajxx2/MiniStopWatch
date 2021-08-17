package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "records")
data class LocalRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val time: Long?,
    val reset: Boolean?
): Serializable