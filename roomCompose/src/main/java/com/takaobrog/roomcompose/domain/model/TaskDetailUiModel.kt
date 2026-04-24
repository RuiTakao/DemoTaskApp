package com.takaobrog.roomcompose.domain.model

class TaskDetailUiModel(
    val uid: Int,
    val title: String,
    val progressPercent: Float,
    val targetDate: String?,
    val isTargetDateOver: Boolean,
)