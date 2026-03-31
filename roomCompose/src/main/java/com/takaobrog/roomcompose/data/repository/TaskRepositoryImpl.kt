package com.takaobrog.roomcompose.data.repository

import com.takaobrog.roomcompose.data.dao.TaskDao
import com.takaobrog.roomcompose.data.model.Task
import com.takaobrog.roomcompose.domain.model.CreateTaskRequest
import com.takaobrog.roomcompose.domain.model.GetTaskResponse
import com.takaobrog.roomcompose.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
) : TaskRepository {
    override fun getList(): Flow<List<GetTaskResponse>> {
        return taskDao.getAll()
            .distinctUntilChanged()
            .map { list ->
                list.map {
                    GetTaskResponse(
                        name = it.name,
                        detail = it.detail ?: ""
                    )
                }
            }
    }

    override suspend fun create(request: CreateTaskRequest): Result<Unit> = runCatching {
        val task = Task(
            name = request.name,
            detail = request.detail,
        )
        taskDao.insert(task)
    }
}