package com.takaobrog.roomcompose.domain.use_case

import com.takaobrog.roomcompose.domain.model.CreateTaskRequest
import com.takaobrog.roomcompose.domain.repository.TaskRepository
import com.takaobrog.roomcompose.util.local_date.TimeProvider
import javax.inject.Inject

class CreateTaskUseCase @Inject constructor(
    private val repository: TaskRepository,
    private val timeProvider: TimeProvider,
) {
    suspend operator fun invoke(
        title: String,
        comment: String,
        targetDate: Long?
    ): Result<Unit> {
        if (title.isBlank()) {
            return Result.failure(CreateTaskException(error = CreateTaskError.TitleEmpty))
        }
        if (title.length > 10) {
            return Result.failure(CreateTaskException(error = CreateTaskError.TitleOver))
        }
        if (comment.length > 30) {
            return Result.failure(CreateTaskException(error = CreateTaskError.CommentOver))
        }
        val createdAt = timeProvider.getNow()
        val request = CreateTaskRequest(
            title = title,
            comment = comment,
            progressPercent = 0.0f,
            targetDate = targetDate,
            createdAt = createdAt
        )
        return repository.create(request)
    }
}