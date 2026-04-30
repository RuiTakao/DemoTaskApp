package com.takaobrog.roomcompose.domain.use_case

import com.takaobrog.roomcompose.domain.repository.TaskRepository
import com.takaobrog.roomcompose.util.local_date.TimeProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class GetTaskDetailUseCase @Inject constructor(
    private val repository: TaskRepository,
    private val timeProvider: TimeProvider,
) {
    operator fun invoke(uid: Int): Flow<TaskDetailUiModel> {
        return repository.getTaskDetail(uid = uid)
            .mapNotNull { task ->
                task?.let {
                    val targetDate = task.targetDate?.let {
                        timeProvider.formatterYmd(targetDate = it)
                    }
                    val isTargetDateOver = timeProvider.isBeforeNow(targetDate = task.targetDate)
                    TaskDetailUiModel(
                        uid = task.uid,
                        title = task.title,
                        comment = task.comment,
                        progressPercent = task.progressPercent,
                        targetDate = targetDate,
                        isTargetDateOver = isTargetDateOver,
                    )
                }
            }
    }
}