package com.takaobrog.roomcompose.presentation.task_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takaobrog.roomcompose.presentation.component.FAButton
import com.takaobrog.roomcompose.presentation.task_list.ui_model.TaskListEvent
import com.takaobrog.roomcompose.domain.model.TaskListUiModel
import com.takaobrog.roomcompose.presentation.component.TaskListItem
import com.takaobrog.roomcompose.presentation.task_list.ui_model.TaskListUiState

@Composable
fun TaskListScreen(
    state: TaskListUiState,
    onEvent: (TaskListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FAButton(onClick = { onEvent(TaskListEvent.OnFabEvent) })
        },
        contentWindowInsets = WindowInsets.systemBars,
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
        ) {
            when (state) {
                TaskListUiState.Loading -> {

                }

                is TaskListUiState.Success -> {
                    val list = state.list
                    ListView(list = list, onEvent = onEvent)
                }

                is TaskListUiState.Error -> {

                }
            }
        }
    }
}

@Composable
fun ListView(list: List<TaskListUiModel>, onEvent: (TaskListEvent) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
    ) {
        items(list) { item ->
            TaskListItem(
                title = item.title,
                progressPercent = item.progressPercent,
                targetDate = item.targetDate,
                isTargetDateOver = item.isTargetDateOver,
                onItemClick = { onEvent(TaskListEvent.OnClickTaskListItemEvent(uid = item.uid)) },
            )
        }
    }
}

@Preview
@Composable
fun TaskListScreenPreview(modifier: Modifier = Modifier) {
    val list = listOf<TaskListUiModel>(
        TaskListUiModel(
            uid = 1,
            title = "Room学習",
            progressPercent = .7f,
            targetDate = null,
            isTargetDateOver = false,
        ),
        TaskListUiModel(
            uid = 2,
            title = "Firebase学習",
            progressPercent = .7f,
            targetDate = null,
            isTargetDateOver = false,
        ),
        TaskListUiModel(
            uid = 3,
            title = "Api学習",
            progressPercent = .7f,
            targetDate = null,
            isTargetDateOver = false,
        ),
    )

    val state = TaskListUiState.Success(list)

    TaskListScreen(state = state, onEvent = {}, modifier = modifier)
}

@Preview
@Composable
fun TaskListScreenPreviewEmpty(modifier: Modifier = Modifier) {
    val state = TaskListUiState.Success(emptyList())
    TaskListScreen(state = state, onEvent = {}, modifier = modifier)
}