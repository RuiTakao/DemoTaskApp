package com.takaobrog.roomcompose.domain.use_case

import com.takaobrog.roomcompose.domain.model.TaskEditUiModel
import com.takaobrog.roomcompose.domain.repository.TaskRepository
import com.takaobrog.roomcompose.util.local_date.TimeProvider
import javax.inject.Inject

class GetTaskEditUseCase @Inject constructor(
    private val repository: TaskRepository,
    private val timeProvider: TimeProvider,
) {
    suspend operator fun invoke(uid: Int): Result<TaskEditUiModel> {
        return repository.getTaskEdit(uid = uid).fold(
            onSuccess = {
                it?.let { task ->
                    val targetDate = task.targetDate?.let {
                        timeProvider.formatterYmd(targetDate = it)
                    }
                    val res = TaskEditUiModel(
                        uid = task.uid,
                        title = task.title,
                        progressPercent = task.progressPercent,
                        targetDate = targetDate,
                    )
                    Result.success(res)
                } ?: Result.failure(IllegalStateException())
            },
            onFailure = { e ->
                Result.failure(exception = e)
            },
        )
    }
}