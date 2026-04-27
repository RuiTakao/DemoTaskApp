package com.takaobrog.roomcompose.presentation.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.takaobrog.roomcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    title: String,
    titleColor: Color = colorResource(id = R.color.black),
    confirmText: String = "OK",
    dismissText: String = "キャンセル",
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { DefaultText(text = title, color = titleColor) },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    DefaultText(text = confirmText)
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    DefaultText(text = dismissText)
                }
            }
        )
    }
}