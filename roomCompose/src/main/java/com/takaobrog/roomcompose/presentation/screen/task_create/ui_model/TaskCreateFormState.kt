package com.takaobrog.roomcompose.presentation.screen.task_create.ui_model

data class TaskCreateFormState(
    val title: String = "",
    val comment: String = "",
    val targetDate: Long? = null,
    val formatTargetDate: String = "",
)