package com.takaobrog.roomcompose.domain.model

data class UpdateTaskEditRequest(
    val uid: Int,
    val title: String,
    val comment: String,
    val progressPercent: Float,
    val targetDate: Long?,
    val updatedAt: String,
)