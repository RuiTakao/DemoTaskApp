package com.takaobrog.roomcompose.presentation.screen.task_create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.takaobrog.roomcompose.R
import com.takaobrog.roomcompose.presentation.component.TaskCreateForm
import com.takaobrog.roomcompose.presentation.screen.task_create.ui_model.TaskCreateEvent
import com.takaobrog.roomcompose.presentation.screen.task_create.ui_model.TaskCreateFormState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCreateScreen(
    formState: TaskCreateFormState,
    onEvent: (TaskCreateEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
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
        TaskCreateForm(
            formState = formState,
            onEvent = onEvent,
            modifier = Modifier.padding(paddingValues = paddingValues),
        )
    }
}
