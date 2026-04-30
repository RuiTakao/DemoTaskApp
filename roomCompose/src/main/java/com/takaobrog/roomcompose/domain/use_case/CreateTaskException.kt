package com.takaobrog.roomcompose.domain.use_case

data class CreateTaskException(val error: CreateTaskError) : Exception()