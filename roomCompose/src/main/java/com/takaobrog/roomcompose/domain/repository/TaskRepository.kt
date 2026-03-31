package com.takaobrog.roomcompose.domain.repository

import com.takaobrog.roomcompose.domain.model.CreateTaskRequest
import com.takaobrog.roomcompose.domain.model.GetTaskResponse
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getList(): Flow<List<GetTaskResponse>>
    suspend fun create(request: CreateTaskRequest): Result<Unit>
}