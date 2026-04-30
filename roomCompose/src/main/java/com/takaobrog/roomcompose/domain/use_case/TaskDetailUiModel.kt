package com.takaobrog.roomcompose.domain.use_case

class TaskDetailUiModel(
    val uid: Int,
    val title: String,
    val comment: String,
    val progressPercent: Float,
    val targetDate: String?,
    val isTargetDateOver: Boolean,
)