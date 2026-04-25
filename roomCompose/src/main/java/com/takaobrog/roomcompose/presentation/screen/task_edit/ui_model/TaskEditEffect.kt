package com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model

sealed class TaskEditEffect {
    data object NavigateBack : TaskEditEffect()
}