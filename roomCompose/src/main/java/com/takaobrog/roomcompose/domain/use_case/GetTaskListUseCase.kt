package com.takaobrog.roomcompose.domain.use_case

import com.takaobrog.roomcompose.domain.model.TaskListUiModel
import com.takaobrog.roomcompose.domain.repository.TaskRepository
import com.takaobrog.roomcompose.util.local_date.TimeProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTaskListUseCase @Inject constructor(
    private val repository: TaskRepository,
    private val timeProvider: TimeProvider,
) {
    operator fun invoke(): Flow<List<TaskListUiModel>> {
        return repository.getList()
            .map { list ->
                list.map { item ->
                    val targetDate = item.targetDate?.let {
                        timeProvider.formatterYmd(targetDate = it)
                    }
                    val isTargetDateOver = timeProvider.isBeforeNow(targetDate = item.targetDate)
                    TaskListUiModel(
                        uid = item.uid,
                        title = item.title,
                        progressPercent = item.progressPercent,
                        targetDate = targetDate,
                        isTargetDateOver = isTargetDateOver,
                    )
                }
            }
    }
}