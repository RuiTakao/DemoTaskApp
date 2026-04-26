package com.takaobrog.roomcompose.domain.use_case

import com.takaobrog.roomcompose.domain.model.UpdateTaskEditRequest
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
        comment: String,
        progressPercent: Float,
        targetDate: Long?
    ): Result<Unit> {
        val updatedAt = timeProvider.getNow()
        val request = UpdateTaskEditRequest(
            uid = uid,
            title = title,
            comment = comment,
            progressPercent = progressPercent,
            targetDate = targetDate,
            updatedAt = updatedAt,
        )
        return repository.update(request = request)
    }
}