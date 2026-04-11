package com.takaobrog.roomcompose.presentation

sealed class ScreenRoute(val route: String) {
    object TaskList : ScreenRoute("task_list")
    object TaskCreate : ScreenRoute("task_create")
    object TaskEdit : ScreenRoute("task_edit")
}