package com.takaobrog.roomcompose.data.repository

import com.takaobrog.roomcompose.data.dao.TaskDao
import com.takaobrog.roomcompose.data.model.Task
import com.takaobrog.roomcompose.domain.model.CreateTaskRequest
import com.takaobrog.roomcompose.domain.model.GetTaskListResponse
import com.takaobrog.roomcompose.domain.model.GetTaskResponse
import com.takaobrog.roomcompose.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
) : TaskRepository {
    override fun getList(): Flow<List<GetTaskListResponse>> {
        return taskDao.getAll()
            .distinctUntilChanged()
            .map { list ->
                list.map {
                    GetTaskListResponse(
                        uid = it.uid,
                        title = it.title,
                        progressPercent = it.progressPercent,
                        targetDate = it.targetDate
                    )
                }
            }
    }

    override suspend fun getTask(uid: Int): Result<GetTaskResponse?> =
        withContext(Dispatchers.IO) {
            runCatching {
                taskDao.getTask(uid = uid)?.let {
                    GetTaskResponse(
                        uid = it.uid,
                        title = it.title,
                        progressPercent = it.progressPercent,
                        targetDate = it.targetDate,
                    )
                }
            }
        }

    override suspend fun create(request: CreateTaskRequest): Result<Unit> = runCatching {
        val task = Task(
            title = request.title,
            progressPercent = request.progressPercent,
            targetDate = request.targetDate,
        )
        taskDao.insert(task)
    }

    override suspend fun delete(uid: Int): Result<Unit> = runCatching {
        taskDao.delete(uid = uid)
    }
}