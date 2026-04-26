package com.takaobrog.roomcompose.presentation.screen.task_create.ui_model

import com.takaobrog.roomcompose.presentation.component.ProgressPercentStatus

data class TaskCreateFormState(
    val title: String = "",
    val comment: String = "",
    val progressPercent: ProgressPercentStatus = ProgressPercentStatus.ZERO,
    val targetDate: Long? = null,
    val formatTargetDate: String = "",
)