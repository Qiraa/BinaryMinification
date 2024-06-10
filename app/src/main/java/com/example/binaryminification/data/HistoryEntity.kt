package com.example.binaryminification.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "input")
    val input: String,
    @ColumnInfo(name = "output")
    val output: String,
    @ColumnInfo(name = "timestamp")
    val timestamp: Long,
)