package com.takaobrog.roomcompose.presentation.screen.task_create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.roomcompose.R
import com.takaobrog.roomcompose.presentation.component.DefaultTopAppBarBack
import com.takaobrog.roomcompose.presentation.component.TaskCreateForm
import com.takaobrog.roomcompose.presentation.screen.task_create.ui_model.TaskCreateEvent
import com.takaobrog.roomcompose.presentation.screen.task_create.ui_model.TaskCreateFormState

@Composable
fun TaskCreateScreen(
    formState: TaskCreateFormState,
    onEvent: (TaskCreateEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
            .systemBarsPadding(),
        topBar = {
            DefaultTopAppBarBack(
                onClick = { onEvent(TaskCreateEvent.OnBackEvent) },
                title = stringResource(id = R.string.task_create_title)
            )
        },
    ) { paddingValues ->
        TaskCreateForm(
            formState = formState,
            onEvent = onEvent,
            modifier = Modifier.padding(paddingValues = paddingValues),
        )
    }
}

@Preview
@Composable
fun TaskCreateScreen_Preview() {
    val formState = TaskCreateFormState(
        title = "Room学習",
        comment = "Dao作成完了",
        targetDate = 1775962688,
        formatTargetDate = "2026/4/12"
    )
    TaskCreateScreen(formState = formState, onEvent = {})
}

@Preview
@Composable
fun TaskCreateScreen_Preview_Empty() {
    val formState = TaskCreateFormState()
    TaskCreateScreen(formState = formState, onEvent = {})
}