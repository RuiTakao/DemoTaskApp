package com.takaobrog.roomcompose.domain.model

data class GetTaskListResponse(
    val uid: Int,
    val title: String,
    val progressPercent: Float,
    val targetDate: String?,
)