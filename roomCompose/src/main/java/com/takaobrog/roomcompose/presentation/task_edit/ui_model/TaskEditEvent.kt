package com.takaobrog.roomcompose.presentation.task_edit.ui_model

sealed class TaskEditEvent {
    data object OnDeleteTaskEvent : TaskEditEvent()
}