package com.takaobrog.roomcompose.domain.use_case

sealed class CreateTaskError {
    object TitleEmpty : CreateTaskError()
    data class TitleOver(val length: Int) : CreateTaskError()
    data class CommentOver(val length: Int) : CreateTaskError()
}