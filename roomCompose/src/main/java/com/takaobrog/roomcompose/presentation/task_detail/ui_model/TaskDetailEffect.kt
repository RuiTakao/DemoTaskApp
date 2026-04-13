package com.takaobrog.roomcompose.presentation.task_detail.ui_model

sealed class TaskDetailEffect {
    data object NavigateBack : TaskDetailEffect()
}
