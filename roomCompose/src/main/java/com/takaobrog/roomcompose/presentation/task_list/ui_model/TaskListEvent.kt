package com.takaobrog.roomcompose.presentation.task_list.ui_model

sealed class TaskListEvent {
    data object OnFabEvent: TaskListEvent()
    data class OnClickTaskListItemEvent(val uid: Int): TaskListEvent()
}