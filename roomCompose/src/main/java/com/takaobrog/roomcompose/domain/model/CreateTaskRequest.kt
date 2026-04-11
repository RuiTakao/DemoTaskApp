package com.takaobrog.roomcompose.domain.model

data class CreateTaskRequest(
    val title: String,
    val progressPercent: Float,
    val targetDate: String?,
)
