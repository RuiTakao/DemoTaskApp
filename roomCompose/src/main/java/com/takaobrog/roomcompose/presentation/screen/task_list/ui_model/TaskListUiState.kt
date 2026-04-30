package com.takaobrog.roomcompose.presentation.screen.task_list.ui_model

import com.takaobrog.roomcompose.domain.use_case.TaskListUiModel

sealed class TaskListUiState {
    data object Loading : TaskListUiState()
    data class Success(val list: List<TaskListUiModel>) : TaskListUiState()
    data class Error(val message: String) : TaskListUiState()
}