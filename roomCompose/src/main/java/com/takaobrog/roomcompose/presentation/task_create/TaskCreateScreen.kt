package com.takaobrog.roomcompose.presentation.task_create

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.takaobrog.roomcompose.R
import com.takaobrog.roomcompose.presentation.task_create.ui_model.TaskCreateEvent
import java.time.Instant
import java.time.ZoneId

private enum class ProgressPercentStatus(val label: String, val data: Float) {
    ZERO("0%", 0.0f),
    ONE("10%", 0.1f),
    TWO("20%", 0.2f),
    THREE("30%", 0.3f),
    FOUR("40%", 0.4f),
    FIVE("50%", 0.5f),
    SIX("60%", 0.6f),
    SEVEN("70%", 0.7f),
    EIGHT("80%", 0.8f),
    NINE("90%", 0.9f),
    MAX("100%", 1.0f),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCreateScreen(
    onEvent: (TaskCreateEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    var title by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selected by rememberSaveable { mutableStateOf(ProgressPercentStatus.ZERO) }
    var isDialog by rememberSaveable { mutableStateOf(false) }
    val dateState = rememberDatePickerState()
    var dateValue by rememberSaveable { mutableStateOf<String?>(null) }


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
            value = title,
            onValueChange = { title = it },
            label = { Text(text = "タスク名入力") }
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selected.label,
                onValueChange = {},
                readOnly = true,
                label = { Text("進捗") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                },
                modifier = Modifier.menuAnchor(
                    type = MenuAnchorType.PrimaryEditable,
                    enabled = true
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                ProgressPercentStatus.entries.forEach {
                    DropdownMenuItem(
                        text = { Text(it.label) },
                        onClick = {
                            selected = it
                            expanded = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = dateValue ?: "",
            onValueChange = {},
            readOnly = true,
            enabled = false,
            label = { Text("日付") },
            modifier = Modifier.clickable {
                isDialog = true
            },
        )

        Button(onClick = {
            onEvent(
                TaskCreateEvent.OnSubmit(
                    name = title,
                    progressPercent = selected.data,
                    targetDate = dateValue,
                )
            )
        }) {
            Text(text = "登録")
        }

        if (isDialog) {
            DatePickerDialog(
                onDismissRequest = { isDialog = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            dateState.selectedDateMillis?.let {
                                val date = Instant.ofEpochMilli(it)
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()
                                dateValue = date.toString()
                            }
                            isDialog = false
                        }
                    ) {
                        Text("OK")
                    }
                }
            ) {
                DatePicker(state = dateState)
            }
        }
    }
}
