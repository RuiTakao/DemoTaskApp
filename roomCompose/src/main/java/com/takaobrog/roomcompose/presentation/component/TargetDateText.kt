package com.takaobrog.roomcompose.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.takaobrog.roomcompose.R

@Composable
fun TargetDateText(targetDate: String?, modifier: Modifier = Modifier) {
    targetDate?.let {
        DefaultText(
            text = stringResource(
                id = R.string.task_list_item_target_date_label,
                targetDate
            ),
            modifier = modifier
        )
    } ?: DefaultText(
        text = stringResource(id = R.string.task_list_item_target_date_empty),
        modifier = modifier
    )
}