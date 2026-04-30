package com.takaobrog.roomcompose.domain.use_case

sealed class CreateTaskError {
    object TitleEmpty : CreateTaskError()
}