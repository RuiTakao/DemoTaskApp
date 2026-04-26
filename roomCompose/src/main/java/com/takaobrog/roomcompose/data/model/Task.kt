package com.takaobrog.roomcompose.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "comment") val comment: String,
    @ColumnInfo(name = "progressPercent") val progressPercent: Float,
    @ColumnInfo(name = "createdAt") val createdAt: String,
    @ColumnInfo(name = "targetDate") val targetDate: Long? = null,
    @ColumnInfo(name = "updatedAt") val updatedAt: String? = null,
)