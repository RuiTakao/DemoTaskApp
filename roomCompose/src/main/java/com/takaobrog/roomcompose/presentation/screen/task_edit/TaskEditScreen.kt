package com.takaobrog.roomcompose.presentation.screen.task_edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takaobrog.roomcompose.R
import com.takaobrog.roomcompose.presentation.component.DateInputField
import com.takaobrog.roomcompose.presentation.component.DefaultButton
import com.takaobrog.roomcompose.presentation.component.DefaultTopAppBarBack
import com.takaobrog.roomcompose.presentation.component.DropdownMenuField
import com.takaobrog.roomcompose.presentation.component.InputTextField
import com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model.TaskEditEvent

@Composable
fun TaskEditScreen(
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
        Column(modifier = modifier.padding(paddingValues)) {
            InputTextField(
                label = stringResource(id = R.string.task_create_form_title),
                value = "",
                onValueChange = { },
            )

            DropdownMenuField(
                label = stringResource(id = R.string.task_create_form_progress_percent),
                value = "",
                onValueChange = {

                }
            )

            DateInputField(
                label = stringResource(id = R.string.task_create_form_target_date),
                value = "",
                onValueChange = { },
            )

            DefaultButton(
                text = stringResource(id = R.string.task_create_form_submit),
                onClick = { },
                modifier = Modifier.padding(top = 16.dp),
            )
        }
    }
}

@Preview
@Composable
fun TaskEditScreen_Preview() {
    TaskEditScreen(onEvent = {})
}