package com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model

sealed class TaskEditEvent {
    data object OnBackEvent : TaskEditEvent()
}