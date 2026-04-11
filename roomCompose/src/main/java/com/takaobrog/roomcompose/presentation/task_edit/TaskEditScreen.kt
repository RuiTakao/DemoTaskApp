package com.takaobrog.roomcompose.presentation.task_edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.takaobrog.roomcompose.presentation.task_edit.ui_model.TaskEditEvent
import com.takaobrog.roomcompose.presentation.task_edit.ui_model.TaskEditUiState

@Composable
fun TaskEditScreen(
    state: TaskEditUiState,
    onEvent: (TaskEditEvent) -> Unit,
    modifier: Modifier = Modifier,
) {

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets.systemBars,
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            when (state) {
                TaskEditUiState.Loading -> {}
                is TaskEditUiState.Success -> {
                    // TODO スタブ
                    Button(onClick = { onEvent(TaskEditEvent.OnDeleteTaskEvent) }) {
                        Text(state.item.title)
                    }
                }

                is TaskEditUiState.Error -> {}
            }
        }
    }
}