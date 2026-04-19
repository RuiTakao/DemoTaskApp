package com.takaobrog.roomcompose.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.takaobrog.roomcompose.presentation.task_create.ui_model.TaskCreateEvent
import com.takaobrog.roomcompose.presentation.task_create.ui_model.TaskCreateFormState

@Composable
fun TaskCreateForm(
    formState: TaskCreateFormState,
    onEvent: (TaskCreateEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        InputTextField(
            label = "タスク名",
            value = formState.title,
            onValueChange = { onEvent(TaskCreateEvent.OnValueChangeTitle(title = it)) },
        )

        DropdownMenuField(
            label = "進捗",
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
            label = "期限",
            value = formState.formatTargetDate,
            onValueChange = { onEvent(TaskCreateEvent.OnValueChangeTargetDate(targetDate = it)) },
        )

        Button(onClick = { onEvent(TaskCreateEvent.OnSubmit) }) {
            Text(text = "登録")
        }
    }
}