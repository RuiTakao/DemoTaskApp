package com.takaobrog.roomcompose.presentation.route

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.takaobrog.roomcompose.presentation.screen.task_list.TaskListScreen
import com.takaobrog.roomcompose.presentation.screen.task_list.TaskListViewModel
import com.takaobrog.roomcompose.presentation.screen.task_list.ui_model.TaskListEvent

fun NavGraphBuilder.taskListRoute(navController: NavHostController) {
    composable(route = ScreenRoute.TaskList.route) {
        val viewModel: TaskListViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsState()

        TaskListScreen(
            state = state,
            onEvent = { event ->
                when (event) {
                    is TaskListEvent.OnClickTaskListItemEvent ->
                        navController.navigate(
                            route = ScreenRoute.TaskDetail.route + "/${event.uid}"
                        )

                    TaskListEvent.OnFabEvent ->
                        navController.navigate(route = ScreenRoute.TaskCreate.route)
                }
            }
        )
    }
}