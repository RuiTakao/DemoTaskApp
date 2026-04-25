package com.takaobrog.roomcompose.domain.model

data class GetTaskDetailResponse(
    val uid: Int,
    val title: String,
    val progressPercent: Float,
    val targetDate: Long?,
)