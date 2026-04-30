package com.takaobrog.roomcompose.presentation.route

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.takaobrog.roomcompose.presentation.component.ErrorDialog
import com.takaobrog.roomcompose.presentation.screen.task_create.TaskCreateScreen
import com.takaobrog.roomcompose.presentation.screen.task_create.TaskCreateViewModel
import com.takaobrog.roomcompose.presentation.screen.task_create.ui_model.TaskCreateEffect
import com.takaobrog.roomcompose.presentation.screen.task_create.ui_model.TaskCreateEvent

fun NavGraphBuilder.taskCreateRoute(navController: NavHostController) {
    composable(route = ScreenRoute.TaskCreate.route) {
        val viewModel: TaskCreateViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    TaskCreateEffect.NavigateBack -> navController.popBackStack()
                }
            }
        }

        uiState.errorMessage?.let {
            ErrorDialog(
                onDismiss = viewModel::onDismiss,
                title = it
            )
        }

        TaskCreateScreen(
            formState = uiState.formState,
            onEvent = { event ->
                when (event) {
                    is TaskCreateEvent.OnSubmit -> viewModel.submit()

                    is TaskCreateEvent.OnValueChangeTitle -> viewModel.inputTitle(
                        title = event.title
                    )

                    is TaskCreateEvent.OnValueChangeComment -> viewModel.inputComment(
                        comment = event.comment
                    )

                    is TaskCreateEvent.OnValueChangeTargetDate -> viewModel.inputTargetDate(
                        targetDate = event.targetDate
                    )

                    TaskCreateEvent.OnBackEvent -> navController.popBackStack()
                }
            },
        )
    }
}