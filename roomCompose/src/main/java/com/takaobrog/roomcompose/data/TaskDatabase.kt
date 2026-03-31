package com.takaobrog.roomcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.takaobrog.roomcompose.data.model.Task
import com.takaobrog.roomcompose.data.dao.TaskDao

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}