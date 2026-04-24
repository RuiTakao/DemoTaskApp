package com.takaobrog.roomcompose.domain.use_case

import com.takaobrog.roomcompose.domain.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskDetailUseCase @Inject constructor(
    private val repository: TaskRepository,
) {
    suspend operator fun invoke(uid: Int): Result<Unit> {
        return repository.delete(uid = uid)
    }
}