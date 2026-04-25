package com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model

import com.takaobrog.roomcompose.presentation.component.ProgressPercentStatus

sealed class TaskEditEvent {
    data object OnSubmit : TaskEditEvent()

    data class OnValueChangeTitle(val title: String) : TaskEditEvent()

    data class OnValueChangeProgressPercent(val progressPercent: ProgressPercentStatus) :
        TaskEditEvent()

    data class OnValueChangeTargetDate(val targetDate: Long?) : TaskEditEvent()

    data object OnBackEvent : TaskEditEvent()
}