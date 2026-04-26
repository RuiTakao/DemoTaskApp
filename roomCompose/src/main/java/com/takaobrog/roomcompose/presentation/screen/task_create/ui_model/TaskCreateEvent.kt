package com.takaobrog.roomcompose.presentation.screen.task_create.ui_model

import com.takaobrog.roomcompose.presentation.component.ProgressPercentStatus

sealed class TaskCreateEvent {
    data object OnSubmit : TaskCreateEvent()

    data class OnValueChangeTitle(val title: String) : TaskCreateEvent()

    data class OnValueChangeComment(val comment: String) : TaskCreateEvent()

    data class OnValueChangeProgressPercent(val progressPercent: ProgressPercentStatus) :
        TaskCreateEvent()

    data class OnValueChangeTargetDate(val targetDate: Long?) : TaskCreateEvent()

    data object OnBackEvent : TaskCreateEvent()
}