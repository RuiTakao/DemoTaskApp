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
        inputValidation(title = title, comment = comment)?.let { message ->
            return Result.failure(CreateTaskException(error = message))
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

    private fun inputValidation(title: String, comment: String): CreateTaskError? {
        if (title.isBlank()) {
            return CreateTaskError.TitleEmpty
        }
        if (title.length > TITLE_OVER_LENGTH) {
            return CreateTaskError.TitleOver(
                length = TITLE_OVER_LENGTH
            )
        }
        if (comment.length > COMMENT_OVER_LENGTH) {
            return CreateTaskError.CommentOver(
                length = COMMENT_OVER_LENGTH
            )
        }
        return null
    }

    companion object {
        private const val TITLE_OVER_LENGTH = 10
        private const val COMMENT_OVER_LENGTH = 30
    }
}