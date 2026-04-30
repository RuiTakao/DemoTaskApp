package com.takaobrog.roomcompose.presentation.screen.task_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takaobrog.roomcompose.R
import com.takaobrog.roomcompose.domain.use_case.TaskDetailUiModel
import com.takaobrog.roomcompose.presentation.component.DefaultTopAppBarBack
import com.takaobrog.roomcompose.presentation.component.DeleteDialog
import com.takaobrog.roomcompose.presentation.component.TaskDetailItem
import com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model.TasKDetailUiState
import com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model.TaskDetailEvent

@Composable
fun TaskDetailScreen(
    state: TasKDetailUiState,
    onEvent: (TaskDetailEvent) -> Unit,
    showDialog: Boolean,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            DefaultTopAppBarBack(onClick = { onEvent(TaskDetailEvent.OnBackEvent) })
        },
        contentWindowInsets = WindowInsets.systemBars,
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            when (state) {
                TasKDetailUiState.Loading -> {}

                is TasKDetailUiState.Success -> {
                    TaskDetailItem(
                        uid = state.item.uid,
                        title = state.item.title,
                        comment = state.item.comment,
                        progressPercent = state.item.progressPercent,
                        targetDate = state.item.targetDate,
                        onEvent = onEvent,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp),
                    )

                    DeleteDialog(
                        showDialog = showDialog,
                        onDismiss = { onEvent(TaskDetailEvent.OnDeleteDismiss) },
                        onConfirm = {
                            onEvent(TaskDetailEvent.OnDeleteDismiss)
                            onEvent(TaskDetailEvent.OnDeleteConfirmClick)
                        },
                        title = stringResource(id = R.string.task_detail_delete_confirm_message),
                    )
                }

                is TasKDetailUiState.Error -> {}
            }
        }
    }
}

@Preview
@Composable
fun TaskDetailScreen_Preview() {
    val item = TaskDetailUiModel(
        uid = 1,
        title = "Room学習",
        comment = "Dao作成\nQuery登録",
        progressPercent = 0.3f,
        targetDate = "2026/4/28",
        isTargetDateOver = true,
    )
    val state = TasKDetailUiState.Success(item = item)
    TaskDetailScreen(
        state = state,
        onEvent = {},
        showDialog = false,
    )
}

@Preview(showBackground = true)
@Composable
fun TaskDetailScreen_Preview_Delete_Dialog() {
    val item = TaskDetailUiModel(
        uid = 1,
        title = "Room学習",
        comment = "Dao作成\nQuery登録",
        progressPercent = 0.3f,
        targetDate = "2026/4/28",
        isTargetDateOver = true,
    )
    val state = TasKDetailUiState.Success(item = item)
    TaskDetailScreen(
        state = state,
        onEvent = {},
        showDialog = true,
    )
}