package com.takaobrog.roomcompose.domain.model

data class TaskListUiModel(
    val title: String,
    val progressPercent: Float,
    val targetDate: String?,
    val isTargetDateOver: Boolean,
)
