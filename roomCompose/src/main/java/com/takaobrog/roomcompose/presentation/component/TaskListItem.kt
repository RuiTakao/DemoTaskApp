package com.takaobrog.roomcompose.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takaobrog.roomcompose.R

@Composable
fun TaskListItem(
    title: String,
    progressPercent: Float,
    targetDate: String?,
    isTargetDateOver: Boolean,
    onItemClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() },
        shape = RoundedCornerShape(size = 16.dp),
        border = BorderStroke(width = 1.dp, color = Color.Black),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        TitleView(title = title, isTargetDateOver = isTargetDateOver)
        DetailView(progressPercent = progressPercent, targetDate = targetDate)
    }
}

@Composable
private fun TitleView(title: String, isTargetDateOver: Boolean) {
    DefaultText(
        text = title,
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.task_list_item_padding),
                top = dimensionResource(id = R.dimen.task_list_item_padding),
                bottom = 8.dp,
            ),
        color = if (!isTargetDateOver) Color.Black else Color.Red,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun DetailView(progressPercent: Float, targetDate: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(
                start = dimensionResource(id = R.dimen.task_list_item_padding),
                top = 10.dp,
                bottom = dimensionResource(id = R.dimen.task_list_item_padding),
            )
    ) {
        ProgressPercentView(progressPercent = progressPercent)
        TargetDateView(targetDate = targetDate)
    }
}

@Composable
private fun ProgressPercentView(progressPercent: Float) {
    Row {
        DefaultText(
            text = stringResource(id = R.string.task_list_item_progress_percent_label),
        )
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

@Composable
private fun TargetDateView(targetDate: String?) {
    TargetDateText(targetDate, modifier = Modifier.padding(top = 8.dp))
}

@Preview
@Composable
fun TaskListItemPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TaskListItem(
            title = "Room学習",
            progressPercent = .3f,
            targetDate = "4/5 12:00",
            isTargetDateOver = false,
            onItemClick = {},
        )
        TaskListItem(
            title = "Firebase学習",
            progressPercent = .7f,
            targetDate = "4/1 9:00",
            isTargetDateOver = true,
            onItemClick = {},
        )
        TaskListItem(
            title = "Firebase学習",
            progressPercent = 1f,
            targetDate = "4/1 9:00",
            isTargetDateOver = true,
            onItemClick = {},
        )
        TaskListItem(
            title = "Api学習",
            progressPercent = .0f,
            targetDate = null,
            isTargetDateOver = false,
            onItemClick = {},
        )
    }
}