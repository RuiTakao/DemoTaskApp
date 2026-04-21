package com.takaobrog.roomcompose.presentation.route

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.takaobrog.roomcompose.presentation.screen.task_detail.TaskDetailScreen
import com.takaobrog.roomcompose.presentation.screen.task_detail.TaskDetailViewModel
import com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model.TaskDetailEffect
import com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model.TaskEditEvent

fun NavGraphBuilder.taskDetailRoute(navController: NavHostController) {
    composable(
        route = ScreenRoute.TaskDetail.route + "/{uid}",
        arguments = listOf(navArgument("uid") { type = NavType.IntType })
    ) {
        val viewModel: TaskDetailViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    TaskDetailEffect.NavigateBack -> navController.popBackStack()
                }
            }
        }

        TaskDetailScreen(
            state = state,
            onEvent = { event ->
                when (event) {
                    is TaskEditEvent.OnDeleteTaskEvent -> viewModel.delete()
                }
            }
        )
    }
}