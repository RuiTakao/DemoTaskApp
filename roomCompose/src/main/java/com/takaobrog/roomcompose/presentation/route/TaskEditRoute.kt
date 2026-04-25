package com.takaobrog.roomcompose.presentation.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.takaobrog.roomcompose.presentation.screen.task_edit.TaskEditScreen
import com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model.TaskEditEvent

fun NavGraphBuilder.taskEditRoute(navController: NavHostController) {
    composable(
        route = ScreenRoute.TaskEdit.route + "/{uid}",
        arguments = listOf(navArgument("uid") { type = NavType.IntType })
    ) {
        TaskEditScreen(
            onEvent = { event ->
                when (event) {
                    TaskEditEvent.OnBackEvent -> navController.popBackStack()
                }
            },
        )
    }
}