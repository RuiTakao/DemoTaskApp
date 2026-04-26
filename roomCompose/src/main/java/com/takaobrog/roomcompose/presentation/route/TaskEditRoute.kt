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
import com.takaobrog.roomcompose.presentation.screen.task_edit.TaskEditScreen
import com.takaobrog.roomcompose.presentation.screen.task_edit.TaskEditViewModel
import com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model.TaskEditEffect
import com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model.TaskEditEvent

fun NavGraphBuilder.taskEditRoute(navController: NavHostController) {
    composable(
        route = ScreenRoute.TaskEdit.route + "/{uid}",
        arguments = listOf(navArgument("uid") { type = NavType.IntType })
    ) {
        val viewModel: TaskEditViewModel = hiltViewModel()
        val formState by viewModel.formState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    TaskEditEffect.NavigateBack -> navController.popBackStack()
                }
            }
        }

        TaskEditScreen(
            formState = formState,
            onEvent = { event ->
                when (event) {
                    TaskEditEvent.OnSubmit -> viewModel.submit()

                    is TaskEditEvent.OnValueChangeTitle -> viewModel.inputTitle(
                        title = event.title
                    )

                    is TaskEditEvent.OnValueChangeComment -> viewModel.inputComment(
                        comment = event.comment
                    )

                    is TaskEditEvent.OnValueChangeProgressPercent -> viewModel.inputProgressPercent(
                        progressPercent = event.progressPercent
                    )

                    is TaskEditEvent.OnValueChangeTargetDate -> viewModel.inputTargetDate(
                        targetDate = event.targetDate
                    )

                    TaskEditEvent.OnBackEvent -> navController.popBackStack()
                }
            },
        )
    }
}