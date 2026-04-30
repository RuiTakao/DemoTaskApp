package com.takaobrog.roomcompose.domain.use_case

data class TaskListUiModel(
    val uid: Int,
    val title: String,
    val progressPercent: Float,
    val targetDate: String?,
    val isTargetDateOver: Boolean,
)