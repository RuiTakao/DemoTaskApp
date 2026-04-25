package com.takaobrog.roomcompose.presentation.screen.task_edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.roomcompose.R
import com.takaobrog.roomcompose.presentation.component.DefaultTopAppBarBack
import com.takaobrog.roomcompose.presentation.component.ProgressPercentStatus
import com.takaobrog.roomcompose.presentation.component.TaskEditForm
import com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model.TaskEditEvent
import com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model.TaskEditFormState

@Composable
fun TaskEditScreen(
    formState: TaskEditFormState,
    onEvent: (TaskEditEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
            .systemBarsPadding(),
        topBar = {
            DefaultTopAppBarBack(
                onClick = { onEvent(TaskEditEvent.OnBackEvent) },
            )
        },
    ) { paddingValues ->
        TaskEditForm(
            formState = formState,
            onEvent = onEvent,
            modifier = Modifier.padding(paddingValues = paddingValues),
        )
    }
}

@Preview
@Composable
fun TaskEditScreen_Preview() {
    TaskEditScreen(
        formState = TaskEditFormState(
            title = "Room学習",
            progressPercent = ProgressPercentStatus.SIX,
            targetDate = 1775962688,
            formatTargetDate = "2026/4/12"
        ),
        onEvent = {},
    )
}