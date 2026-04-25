package com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model

sealed class TaskDetailEvent {
    data object OnDeleteTaskEvent : TaskDetailEvent()
    data class OnEditTaskEvent(val uid: Int) : TaskDetailEvent()
    data object OnBackEvent : TaskDetailEvent()
}