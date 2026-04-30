package com.takaobrog.roomcompose.presentation.screen.task_create.ui_model

data class TaskCreateUiState(
    val formState: TaskCreateFormState,
    val errorMessage: String? = null
)
