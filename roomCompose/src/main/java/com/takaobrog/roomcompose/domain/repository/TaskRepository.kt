package com.takaobrog.roomcompose.domain.repository

import com.takaobrog.roomcompose.domain.model.CreateTaskRequest
import com.takaobrog.roomcompose.domain.model.GetTaskListResponse
import com.takaobrog.roomcompose.domain.model.GetTaskDetailResponse
import com.takaobrog.roomcompose.domain.model.GetTaskEditResponse
import com.takaobrog.roomcompose.domain.model.UpdateTaskEditRequest
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getList(): Flow<List<GetTaskListResponse>>
    fun getTaskDetail(uid: Int): Flow<GetTaskDetailResponse?>
    suspend fun create(request: CreateTaskRequest): Result<Unit>
    suspend fun getTaskEdit(uid: Int): Result<GetTaskEditResponse?>
    suspend fun update(request: UpdateTaskEditRequest): Result<Unit>
    suspend fun delete(uid: Int): Result<Unit>
}