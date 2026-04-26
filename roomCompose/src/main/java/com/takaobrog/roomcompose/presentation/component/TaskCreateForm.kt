package com.takaobrog.roomcompose.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takaobrog.roomcompose.R
import com.takaobrog.roomcompose.presentation.screen.task_create.ui_model.TaskCreateEvent
import com.takaobrog.roomcompose.presentation.screen.task_create.ui_model.TaskCreateFormState

@Composable
fun TaskCreateForm(
    formState: TaskCreateFormState,
    onEvent: (TaskCreateEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        InputTextField(
            label = stringResource(id = R.string.task_create_form_title),
            value = formState.title,
            onValueChange = { onEvent(TaskCreateEvent.OnValueChangeTitle(title = it)) },
        )

        ProgressPercentInputField(
            label = stringResource(id = R.string.task_create_form_progress_percent),
            value = formState.progressPercent.label,
            onValueChange = {
                onEvent(
                    TaskCreateEvent.OnValueChangeProgressPercent(
                        progressPercent = it
                    )
                )
            }
        )

        DateInputField(
            label = stringResource(id = R.string.task_create_form_target_date),
            value = formState.formatTargetDate,
            onValueChange = { onEvent(TaskCreateEvent.OnValueChangeTargetDate(targetDate = it)) },
        )

        InputTextAreaField(
            label = stringResource(id = R.string.task_create_form_comment),
            value = formState.comment,
            onValueChange = { onEvent(TaskCreateEvent.OnValueChangeComment(comment = it)) },
        )

        DefaultButton(
            text = stringResource(id = R.string.task_create_form_submit),
            onClick = { onEvent(TaskCreateEvent.OnSubmit) },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        )
    }
}

@Preview
@Composable
fun TaskCreateForm_Preview() {
    TaskCreateForm(
        formState = TaskCreateFormState(),
        onEvent = {}
    )
}

@Preview
@Composable
fun TaskCreateForm_Preview_InValue() {
    TaskCreateForm(
        formState = TaskCreateFormState(
            title = "Room学習",
            progressPercent = ProgressPercentStatus.SIX,
            targetDate = 1775962688,
            formatTargetDate = "2026/4/12",
        ),
        onEvent = {}
    )
}