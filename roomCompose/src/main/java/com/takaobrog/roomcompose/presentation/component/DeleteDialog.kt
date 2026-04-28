package com.takaobrog.roomcompose.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.roomcompose.R

@Composable
fun DeleteDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    title: String,
) {
    DefaultDialog(
        showDialog = showDialog,
        onDismiss = onDismiss,
        onConfirm = onConfirm,
        title = title,
        titleColor = colorResource(id = R.color.danger),
    )
}

@Preview
@Composable
fun DeleteDialog_Preview() {
    DeleteDialog(
        showDialog = true,
        onDismiss = {},
        onConfirm = {},
        title = "削除しますか？",
    )
}