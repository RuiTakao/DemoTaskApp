package com.takaobrog.roomcompose.presentation.task_create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.roomcompose.R
import com.takaobrog.roomcompose.presentation.task_create.ui_model.TaskCreateEvent

@Composable
fun TaskCreateScreen(
    onEvent: (TaskCreateEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    var name by rememberSaveable { mutableStateOf("") }
    var detail by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
            .systemBarsPadding()
    ) {
        TextButton(onClick = { onEvent(TaskCreateEvent.OnBackEvent) }) {
            // TODO スタブ
            Text("< 戻る")
        }

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "タスク名入力") }
        )
        TextField(
            value = detail,
            onValueChange = { detail = it },
            label = { Text(text = "タスク詳細入力") }
        )
        Button(onClick = { onEvent(TaskCreateEvent.OnSubmit(name = name, detail = detail)) }) {
            Text(text = "登録")
        }
    }
}

@Preview
@Composable
fun TaskCreateScreenPreview(modifier: Modifier = Modifier) {
    TaskCreateScreen(onEvent = {}, modifier = modifier)
}