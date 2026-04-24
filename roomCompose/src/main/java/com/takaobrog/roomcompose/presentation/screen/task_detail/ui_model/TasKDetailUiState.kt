package com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model

import com.takaobrog.roomcompose.domain.model.TaskDetailUiModel

sealed class TasKDetailUiState {
    data object Loading : TasKDetailUiState()
    data class Success(val item: TaskDetailUiModel) : TasKDetailUiState()
    data class Error(val message: String?) : TasKDetailUiState()
}
