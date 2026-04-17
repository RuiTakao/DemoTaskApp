package com.takaobrog.roomcompose.presentation.task_create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.takaobrog.roomcompose.R
import com.takaobrog.roomcompose.presentation.component.DateInputField
import com.takaobrog.roomcompose.presentation.component.DropdownMenuField
import com.takaobrog.roomcompose.presentation.component.InputTextField
import com.takaobrog.roomcompose.presentation.component.ProgressPercentStatus
import com.takaobrog.roomcompose.presentation.task_create.ui_model.TaskCreateEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCreateScreen(
    targetDate: String,
    onEvent: (TaskCreateEvent) -> Unit,
    onValueChangeTargetDate: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    var title by rememberSaveable { mutableStateOf("") }
    var selected by rememberSaveable { mutableStateOf(ProgressPercentStatus.ZERO) }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
            .systemBarsPadding(),
        topBar = {
            TopAppBar(
                title = { Text("タスク作成") },
                navigationIcon = {
                    IconButton(onClick = { onEvent(TaskCreateEvent.OnBackEvent) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "戻る",
                            tint = Color(0xFF854A2A)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.LightGray)
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues),
        ) {
            InputTextField(
                label = "タスク名",
                value = title,
                onValueChange = { title = it },
            )

            DropdownMenuField(
                label = "進捗",
                value = selected.label,
                onValueChange = { selected = it }
            )

            DateInputField(
                label = "期限",
                value = targetDate,
                onValueChange = { onValueChangeTargetDate(it) }
            )

            Button(onClick = {
                onEvent(
                    TaskCreateEvent.OnSubmit(
                        name = title,
                        progressPercent = selected.data,
                        targetDate = targetDate,
                    )
                )
            }) {
                Text(text = "登録")
            }
        }
    }


}
