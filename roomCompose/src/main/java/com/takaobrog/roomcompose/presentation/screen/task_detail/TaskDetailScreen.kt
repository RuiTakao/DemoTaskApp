package com.takaobrog.roomcompose.presentation.screen.task_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model.TasKDetailUiState
import com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model.TaskEditEvent

@Composable
fun TaskDetailScreen(
    state: TasKDetailUiState,
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
                TasKDetailUiState.Loading -> {}
                is TasKDetailUiState.Success -> {
                    // TODO スタブ
                    Button(onClick = { onEvent(TaskEditEvent.OnDeleteTaskEvent) }) {
                        Text(state.item.title)
                    }
                }

                is TasKDetailUiState.Error -> {}
            }
        }
    }
}