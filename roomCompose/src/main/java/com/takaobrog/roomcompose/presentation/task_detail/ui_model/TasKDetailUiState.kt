package com.takaobrog.roomcompose.presentation.task_detail.ui_model

import com.takaobrog.roomcompose.domain.model.GetTaskResponse

sealed class TasKDetailUiState {
    data object Loading : TasKDetailUiState()
    data class Success(val item: GetTaskResponse) : TasKDetailUiState()
    data class Error(val message: String?) : TasKDetailUiState()
}
