package com.takaobrog.roomcompose.presentation.screen.task_create.ui_model

import com.takaobrog.roomcompose.domain.use_case.CreateTaskError

data class TaskCreateUiState(
    val formState: TaskCreateFormState,
    val errorMessage: CreateTaskError? = null
)
