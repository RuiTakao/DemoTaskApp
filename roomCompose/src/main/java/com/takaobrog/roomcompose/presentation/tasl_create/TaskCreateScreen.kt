package com.takaobrog.roomcompose.presentation.tasl_create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.roomcompose.R

@Composable
fun TaskCreateScreen(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
            .systemBarsPadding()
    ) {
        TextButton(onClick = onClick) {
            // TODO スタブ
            Text("< 戻る")
        }
    }
}

@Preview
@Composable
fun TaskCreateScreenPreview(modifier: Modifier = Modifier) {
    TaskCreateScreen(onClick = {}, modifier = modifier)
}