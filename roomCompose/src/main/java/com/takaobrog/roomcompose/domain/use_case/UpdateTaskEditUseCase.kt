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
        inputValidation(title = title, comment = comment)?.let { error ->
            return Result.failure(exception = UpdateTaskEditException(error = error))
        }
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

    private fun inputValidation(title: String, comment: String): UpdateTaskEditError? {
        if (title.isBlank()) {
            return UpdateTaskEditError.TitleEmpty
        }
        if (title.length > TITLE_OVER_LENGTH) {
            return UpdateTaskEditError.TitleOver(
                length = TITLE_OVER_LENGTH
            )
        }
        if (comment.length > COMMENT_OVER_LENGTH) {
            return UpdateTaskEditError.CommentOver(
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