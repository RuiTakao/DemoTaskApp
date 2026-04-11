package com.takaobrog.roomcompose.domain.repository

import com.takaobrog.roomcompose.domain.model.CreateTaskRequest
import com.takaobrog.roomcompose.domain.model.GetTaskListResponse
import com.takaobrog.roomcompose.domain.model.GetTaskResponse
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getList(): Flow<List<GetTaskListResponse>>
    fun getTask(uid: Int): Flow<GetTaskResponse>
    suspend fun create(request: CreateTaskRequest): Result<Unit>
}