package com.takaobrog.roomcompose.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.takaobrog.roomcompose.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<Task>>

    @Insert
    suspend fun insert(vararg task: Task)
}