package com.takaobrog.roomcompose.domain.use_case

import com.takaobrog.roomcompose.domain.model.CreateTaskRequest
import com.takaobrog.roomcompose.domain.repository.TaskRepository
import javax.inject.Inject

class CreateTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(name: String, progressPercent: Float, targetDate: String?) {
        val request = CreateTaskRequest(
            title = name,
            progressPercent = progressPercent,
            targetDate = targetDate
        )
        repository.create(request)
    }
}