package com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model

import com.takaobrog.roomcompose.domain.use_case.UpdateTaskEditError

data class TaskEditUiState(
    val loading: Boolean = true,
    val formState: TaskEditFormState = TaskEditFormState(),
    val validError: UpdateTaskEditError? = null,
    val updateError: String? = null,
    val loadError: String? = null,
)