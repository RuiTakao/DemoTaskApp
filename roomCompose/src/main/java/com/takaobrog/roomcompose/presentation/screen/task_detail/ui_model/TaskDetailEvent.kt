package com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model

sealed class TaskDetailEvent {
    data object OnDeleteTaskEvent : TaskDetailEvent()
    data object OnBackEvent : TaskDetailEvent()
}