package com.takaobrog.roomcompose.presentation.component.progress_percent

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.roomcompose.presentation.component.DefaultText

@Composable
fun ProgressPercentItem(label: String, progressPercent: Float) {
    Row {
        DefaultText(text = "$label: ")
        ProgressPercent(progressPercent = progressPercent)
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressPercentView_Preview() {
    ProgressPercentItem(label = "進捗", progressPercent = 0.3f)
}

@Preview(showBackground = true)
@Composable
fun ProgressPercentView_Preview_Min() {
    ProgressPercentItem(label = "進捗", progressPercent = 0f)
}

@Preview(showBackground = true)
@Composable
fun ProgressPercentView_Preview_Max() {
    ProgressPercentItem(label = "進捗", progressPercent = 1f)
}