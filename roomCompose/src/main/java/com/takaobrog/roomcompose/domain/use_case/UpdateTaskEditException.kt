package com.takaobrog.roomcompose.domain.use_case

data class UpdateTaskEditException(val error: UpdateTaskEditError) : Exception()