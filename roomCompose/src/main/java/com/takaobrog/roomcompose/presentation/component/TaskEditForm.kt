package com.takaobrog.roomcompose.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takaobrog.roomcompose.R
import com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model.TaskEditEvent
import com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model.TaskEditFormState

@Composable
fun TaskEditForm(
    formState: TaskEditFormState,
    onEvent: (TaskEditEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        InputTextField(
            label = stringResource(id = R.string.task_edit_form_title),
            value = formState.title,
            onValueChange = { onEvent(TaskEditEvent.OnValueChangeTitle(title = it)) },
        )

        ProgressPercentInputField(
            label = stringResource(id = R.string.task_edit_form_progress_percent),
            value = formState.progressPercent.label,
            onValueChange = { onEvent(TaskEditEvent.OnValueChangeProgressPercent(progressPercent = it)) }
        )

        DateInputField(
            label = stringResource(id = R.string.task_edit_form_target_date),
            value = formState.formatTargetDate,
            onValueChange = { onEvent(TaskEditEvent.OnValueChangeTargetDate(targetDate = it)) },
        )

        InputTextAreaField(
            label = stringResource(id = R.string.task_edit_form_comment),
            value = formState.comment,
            onValueChange = { onEvent(TaskEditEvent.OnValueChangeComment(comment = it)) },
        )

        Row(
            Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DefaultButton(
                text = stringResource(id = R.string.task_edit_form_submit_ok),
                onClick = { onEvent(TaskEditEvent.OnSubmit) },
            )
            DefaultButton(
                text = stringResource(id = R.string.task_edit_form_submit_cancel),
                onClick = { onEvent(TaskEditEvent.OnBackEvent) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskEditForm_Preview() {
    TaskEditForm(
        formState = TaskEditFormState(
            title = "Room学習",
            progressPercent = ProgressPercentStatus.SIX,
            targetDate = 1775962688,
            formatTargetDate = "2026/4/12"
        ),
        onEvent = {},
    )
}