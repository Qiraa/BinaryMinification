package com.example.binaryminification.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryEntityDao {

    @Query("SELECT * FROM historyentity")
    suspend fun getAll(): List<HistoryEntity>

    @Insert
    suspend fun insertAll(vararg entity: HistoryEntity)

    @Query("DELETE FROM historyentity")
    suspend fun deleteAll()
}