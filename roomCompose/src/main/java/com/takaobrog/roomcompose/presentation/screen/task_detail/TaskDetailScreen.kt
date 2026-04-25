package com.takaobrog.roomcompose.presentation.screen.task_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takaobrog.roomcompose.R
import com.takaobrog.roomcompose.presentation.component.DefaultText
import com.takaobrog.roomcompose.presentation.component.DefaultTopAppBarBack
import com.takaobrog.roomcompose.presentation.component.ProgressPercentItem
import com.takaobrog.roomcompose.presentation.component.TargetDateText
import com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model.TasKDetailUiState
import com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model.TaskDetailEvent

@Composable
fun TaskDetailScreen(
    state: TasKDetailUiState,
    onEvent: (TaskDetailEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            DefaultTopAppBarBack(
                onClick = { onEvent(TaskDetailEvent.OnBackEvent) },
                title = "タスク詳細"
            )
        },
        contentWindowInsets = WindowInsets.systemBars,
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            when (state) {
                TasKDetailUiState.Loading -> {}
                is TasKDetailUiState.Success -> {
                    DetailView(
                        title = state.item.title,
                        progressPercent = state.item.progressPercent,
                        targetDate = state.item.targetDate,
                    )
                    // TODO スタブ
                    Button(onClick = { onEvent(TaskDetailEvent.OnDeleteTaskEvent) }) {
                        Text(state.item.title)
                    }
                }

                is TasKDetailUiState.Error -> {}
            }
        }
    }
}

@Composable
fun DetailView(
    title: String,
    modifier: Modifier = Modifier,
    progressPercent: Float = 0f,
    targetDate: String? = null,
) {
    Column(
        modifier = modifier
    ) {
        DefaultText(text = title)
        ProgressPercentItem(
            label = stringResource(id = R.string.task_list_item_progress_percent_label),
            progressPercent = progressPercent,
        )
        TargetDateText(targetDate, modifier = Modifier.padding(top = 8.dp))
    }
}

@Preview
@Composable
fun DetailView_Preview() {
    Column(Modifier.background(Color.White)) {
        DetailView(
            title = "Room学習"
        )
    }
}