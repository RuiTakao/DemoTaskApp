package com.takaobrog.roomcompose.domain.use_case

import com.takaobrog.roomcompose.domain.model.CreateTaskRequest
import com.takaobrog.roomcompose.domain.repository.TaskRepository
import com.takaobrog.roomcompose.util.local_date.TimeProvider
import javax.inject.Inject

class CreateTaskUseCase @Inject constructor(
    private val repository: TaskRepository,
    private val timeProvider: TimeProvider,
) {
    suspend operator fun invoke(title: String, progressPercent: Float, targetDate: Long?) {
        val createdAt = timeProvider.getNow()
        val request = CreateTaskRequest(
            title = title,
            progressPercent = progressPercent,
            targetDate = targetDate,
            createdAt = createdAt
        )
        repository.create(request)
    }
}