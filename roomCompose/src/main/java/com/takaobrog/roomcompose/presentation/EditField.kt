package com.takaobrog.roomcompose.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditField(
    onClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var detail by remember { mutableStateOf("") }
    Column(
        modifier = modifier.padding(top = 20.dp)
    ) {
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
        Button(onClick = { onClick(name, detail) }) {
            Text(text = "登録")
        }
    }
}