package com.takaobrog.roomcompose.presentation.route

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.takaobrog.roomcompose.R
import com.takaobrog.roomcompose.domain.use_case.UpdateTaskEditError
import com.takaobrog.roomcompose.presentation.component.ErrorDialog
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
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    TaskEditEffect.NavigateBack -> navController.popBackStack()
                }
            }
        }

        uiState.validError?.let { validError ->
            ErrorDialog(
                onDismiss = viewModel::onDismissValidError,
                title = when (validError) {
                    UpdateTaskEditError.TitleEmpty -> stringResource(id = R.string.task_edit_form_title_valid_empty)

                    is UpdateTaskEditError.TitleOver -> stringResource(
                        id = R.string.task_edit_form_title_valid_over,
                        validError.length
                    )

                    is UpdateTaskEditError.CommentOver -> stringResource(
                        id = R.string.task_edit_form_comment_valid_over,
                        validError.length
                    )
                },
            )
        }

        TaskEditScreen(
            formState = uiState.formState,
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