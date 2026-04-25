package com.takaobrog.roomcompose.domain.use_case

import com.takaobrog.roomcompose.domain.model.TaskDetailUiModel
import com.takaobrog.roomcompose.domain.repository.TaskRepository
import com.takaobrog.roomcompose.util.local_date.TimeProvider
import javax.inject.Inject

class GetTaskDetailUseCase @Inject constructor(
    private val repository: TaskRepository,
    private val timeProvider: TimeProvider,
) {
    suspend operator fun invoke(uid: Int): Result<TaskDetailUiModel> {
        return repository.getTaskDetail(uid = uid).fold(
            onSuccess = {
                it?.let { task ->
                    val targetDate = task.targetDate?.let {
                        timeProvider.formatterYmd(targetDate = it)
                    }
                    val isTargetDateOver = timeProvider.isBeforeNow(targetDate = task.targetDate)
                    val res = TaskDetailUiModel(
                        uid = task.uid,
                        title = task.title,
                        progressPercent = task.progressPercent,
                        targetDate = targetDate,
                        isTargetDateOver = isTargetDateOver,
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