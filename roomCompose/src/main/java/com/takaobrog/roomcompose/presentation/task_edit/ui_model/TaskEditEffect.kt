package com.takaobrog.roomcompose.presentation.task_edit.ui_model

sealed class TaskEditEffect {
    data object NavigateBack : TaskEditEffect()
}
