package com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model

sealed class TaskEditEvent {
    data object OnDeleteTaskEvent : TaskEditEvent()
}