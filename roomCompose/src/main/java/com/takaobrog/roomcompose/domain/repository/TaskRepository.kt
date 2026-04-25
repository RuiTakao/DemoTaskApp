package com.takaobrog.roomcompose.domain.repository

import com.takaobrog.roomcompose.domain.model.CreateTaskRequest
import com.takaobrog.roomcompose.domain.model.GetTaskListResponse
import com.takaobrog.roomcompose.domain.model.GetTaskResponse
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getList(): Flow<List<GetTaskListResponse>>
    suspend fun getTaskDetail(uid: Int): Result<GetTaskResponse?>
    suspend fun create(request: CreateTaskRequest): Result<Unit>
    suspend fun delete(uid: Int): Result<Unit>
}