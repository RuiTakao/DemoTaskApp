package com.takaobrog.roomcompose.presentation.task_detail.ui_model

sealed class TaskEditEvent {
    data object OnDeleteTaskEvent : TaskEditEvent()
}