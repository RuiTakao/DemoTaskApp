package com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model

sealed class TaskDetailEffect {
    data object NavigateBack : TaskDetailEffect()
}
