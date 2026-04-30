package com.takaobrog.roomcompose.domain.use_case

sealed class UpdateTaskEditError {
    object TitleEmpty : UpdateTaskEditError()
    data class TitleOver(val length: Int) : UpdateTaskEditError()
    data class CommentOver(val length: Int) : UpdateTaskEditError()
}