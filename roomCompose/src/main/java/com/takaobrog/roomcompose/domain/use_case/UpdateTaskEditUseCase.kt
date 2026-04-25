package com.takaobrog.roomcompose.domain.use_case

import com.takaobrog.roomcompose.domain.repository.TaskRepository
import com.takaobrog.roomcompose.util.local_date.TimeProvider
import javax.inject.Inject

class UpdateTaskEditUseCase @Inject constructor(
    private val repository: TaskRepository,
    private val timeProvider: TimeProvider,
) {
    suspend operator fun invoke(
        uid: Int,
        title: String,
        progressPercent: Float,
        targetDate: Long?
    ): Result<Unit> {
        val updatedAt = timeProvider.getNow()
        return repository.update(
            uid = uid,
            title = title,
            progressPercent = progressPercent,
            targetDate = targetDate,
            updatedAt = updatedAt,
        )
    }
}