package com.takaobrog.roomcompose.presentation.task_edit.ui_model

import com.takaobrog.roomcompose.domain.model.GetTaskResponse

sealed class TaskEditUiState {
    data object Loading : TaskEditUiState()
    data class Success(val item: GetTaskResponse) : TaskEditUiState()
    data class Error(val message: String?) : TaskEditUiState()
}
