package com.takaobrog.roomcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.takaobrog.roomcompose.presentation.task_list.TaskListScreen
import com.takaobrog.roomcompose.presentation.task_list.TaskListViewModel
import com.takaobrog.roomcompose.presentation.task_create.TaskCreateScreen
import com.takaobrog.roomcompose.presentation.task_create.ui_model.TaskCreateEffect
import com.takaobrog.roomcompose.presentation.task_create.ui_model.TaskCreateEvent
import com.takaobrog.roomcompose.presentation.task_create.TaskCreateViewModel
import com.takaobrog.roomcompose.presentation.task_detail.TaskDetailScreen
import com.takaobrog.roomcompose.presentation.task_detail.TaskDetailViewModel
import com.takaobrog.roomcompose.presentation.task_detail.ui_model.TaskDetailEffect
import com.takaobrog.roomcompose.presentation.task_detail.ui_model.TaskEditEvent
import com.takaobrog.roomcompose.presentation.task_list.ui_model.TaskListEvent
import com.takaobrog.roomcompose.presentation.ui.theme.DemoTaskAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoTaskAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ScreenRoute.TaskList.route
                ) {
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

                    composable(route = ScreenRoute.TaskCreate.route) {
                        val viewModel: TaskCreateViewModel = hiltViewModel()
                        val formState by viewModel.formState.collectAsState()

                        LaunchedEffect(Unit) {
                            viewModel.effect.collect { effect ->
                                when (effect) {
                                    TaskCreateEffect.NavigateBack -> navController.popBackStack()
                                }
                            }
                        }

                        TaskCreateScreen(
                            formState = formState,
                            onEvent = { event ->
                                when (event) {
                                    is TaskCreateEvent.OnSubmit -> viewModel.submit()

                                    is TaskCreateEvent.OnValueChangeTitle -> viewModel.inputTitle(
                                        title = event.title
                                    )

                                    is TaskCreateEvent.OnValueChangeProgressPercent -> viewModel.inputProgressPercent(
                                        progressPercent = event.progressPercent
                                    )

                                    is TaskCreateEvent.OnValueChangeTargetDate -> viewModel.inputTargetDate(
                                        targetDate = event.targetDate
                                    )

                                    TaskCreateEvent.OnBackEvent -> navController.popBackStack()
                                }
                            },
                        )
                    }

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
            }
        }
    }
}