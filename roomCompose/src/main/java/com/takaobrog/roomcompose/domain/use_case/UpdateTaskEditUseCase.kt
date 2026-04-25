package com.takaobrog.roomcompose.domain.use_case

import com.takaobrog.roomcompose.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskEditUseCase @Inject constructor(
    private val repository: TaskRepository,
) {
    suspend operator fun invoke(
        uid: Int,
        title: String,
        progressPercent: Float,
        targetDate: Long?
    ): Result<Unit> {
        return repository.update(
            uid = uid,
            title = title,
            progressPercent = progressPercent,
            targetDate = targetDate,
        )
    }
}