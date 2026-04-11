package com.takaobrog.roomcompose.domain.use_case

import com.takaobrog.roomcompose.domain.model.TaskListUiModel
import com.takaobrog.roomcompose.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class GetTaskListUseCase @Inject constructor(
    private val repository: TaskRepository,
) {
    operator fun invoke(): Flow<List<TaskListUiModel>> {
        return repository.getList()
            .map { list ->
                list.map { item ->
                    val targetDate =
                        item.targetDate?.let { if (it.isNotEmpty()) LocalDate.parse(it) else null }
                    val isTargetDateOver = targetDate?.isBefore(LocalDate.now())
                    TaskListUiModel(
                        title = item.title,
                        progressPercent = item.progressPercent,
                        targetDate = item.targetDate,
                        isTargetDateOver = isTargetDateOver == true,
                    )
                }
            }
    }
}