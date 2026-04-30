package com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model

import com.takaobrog.roomcompose.domain.use_case.UpdateTaskEditError

sealed class TaskEditUiState {
    data object Loading : TaskEditUiState()
    data class Success(val formState: TaskEditUiState, val error: UpdateTaskEditError) :
        TaskEditUiState()

    data class Error(val message: String?) : TaskEditUiState()
}