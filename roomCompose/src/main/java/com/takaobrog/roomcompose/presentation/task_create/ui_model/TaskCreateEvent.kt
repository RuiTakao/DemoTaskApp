package com.takaobrog.roomcompose.presentation.task_create.ui_model

sealed class TaskCreateEvent {
    data class OnSubmit(val name: String, val detail: String) : TaskCreateEvent()
    data object OnBackEvent : TaskCreateEvent()
}