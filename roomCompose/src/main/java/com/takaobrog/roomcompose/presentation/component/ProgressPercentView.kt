package com.takaobrog.roomcompose.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProgressPercentView(label: String, progressPercent: Float) {
    Row {
        DefaultText(text = label)
        LinearProgressIndicator(
            progress = { progressPercent },
            modifier = Modifier
                .height(height = 16.dp)
                .padding(start = 4.dp),
            color = Color.Green,
            trackColor = Color.Black,
        )
    }
}