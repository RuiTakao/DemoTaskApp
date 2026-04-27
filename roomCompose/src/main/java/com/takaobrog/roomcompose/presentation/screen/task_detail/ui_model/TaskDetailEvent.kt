package com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model

sealed class TaskDetailEvent {
    data object OnDeleteConfirmClick : TaskDetailEvent()
    data object OnDeleteExecute : TaskDetailEvent()
    data object OnDeleteDismiss : TaskDetailEvent()
    data class OnEditTaskEvent(val uid: Int) : TaskDetailEvent()
    data object OnBackEvent : TaskDetailEvent()
}