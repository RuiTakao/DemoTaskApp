package com.takaobrog.roomcompose.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.roomcompose.R

@Composable
fun TargetDateText(targetDate: String?, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        DefaultText(
            text = "${stringResource(id = R.string.task_list_item_target_date_label)}: ",
            fontWeight = FontWeight.Bold,
        )
        targetDate?.let {
            DefaultText(text = targetDate)
        } ?: DefaultText(
            text = stringResource(id = R.string.task_list_item_target_date_empty)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TargetDateText_Preview() {
    TargetDateText(targetDate = "2026/4/30")
}

@Preview(showBackground = true)
@Composable
fun TargetDateText_Preview_null() {
    TargetDateText(targetDate = null)
}