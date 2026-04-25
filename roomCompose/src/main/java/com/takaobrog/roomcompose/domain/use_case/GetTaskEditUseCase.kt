package com.takaobrog.roomcompose.domain.use_case

import com.takaobrog.roomcompose.domain.model.GetTaskEditResponse
import com.takaobrog.roomcompose.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskEditUseCase @Inject constructor(
    private val repository: TaskRepository,
) {
    suspend operator fun invoke(uid: Int): Result<GetTaskEditResponse> {
        return repository.getTaskEdit(uid = uid).fold(
            onSuccess = {
                it?.let { res ->
                    Result.success(res)
                } ?: Result.failure(IllegalStateException())
            },
            onFailure = { e ->
                Result.failure(exception = e)
            },
        )
    }
}