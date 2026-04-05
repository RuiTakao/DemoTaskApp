package com.takaobrog.roomcompose.presentation.task_create.ui_model

sealed class TaskCreateEffect {
    data object NavigateBack : TaskCreateEffect()
}
