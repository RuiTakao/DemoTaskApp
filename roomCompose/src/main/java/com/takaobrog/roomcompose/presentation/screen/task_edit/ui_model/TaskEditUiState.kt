package com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model

sealed class TaskEditUiState {
    data object Loading : TaskEditUiState()
    data object Success : TaskEditUiState()
    data class Error(val message: String?) : TaskEditUiState()
}