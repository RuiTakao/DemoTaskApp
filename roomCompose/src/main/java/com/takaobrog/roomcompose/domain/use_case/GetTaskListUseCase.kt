package com.takaobrog.roomcompose.domain.use_case

import com.takaobrog.roomcompose.domain.model.GetTaskResponse
import com.takaobrog.roomcompose.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class GetTaskListUseCase @Inject constructor(
    private val repository: TaskRepository,
) {
    operator fun invoke(): Flow<List<GetTaskResponse>> {
        return repository.getList().distinctUntilChanged()
    }
}