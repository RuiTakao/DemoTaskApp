package com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model

import com.takaobrog.roomcompose.presentation.component.ProgressPercentStatus

data class TaskEditFormState(
    val title: String = "",
    val progressPercent: ProgressPercentStatus = ProgressPercentStatus.ZERO,
    val targetDate: Long? = null,
    val formatTargetDate: String = "",
)
