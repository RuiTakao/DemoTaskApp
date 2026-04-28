package com.takaobrog.roomcompose.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.takaobrog.roomcompose.R
import com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model.TaskDetailEvent

@Composable
fun TaskDetailItem(
    uid: Int,
    title: String,
    comment: String,
    progressPercent: Float,
    targetDate: String?,
    onEvent: (TaskDetailEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        DefaultText(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        ProgressPercentItem(
            label = stringResource(id = R.string.task_list_item_progress_percent_label),
            progressPercent = progressPercent,
            modifier = Modifier.padding(top = 16.dp),
        )
        TargetDateText(targetDate, modifier = Modifier.padding(top = 8.dp))
        DefaultText(text = comment, modifier = Modifier.padding(top = 8.dp))
        DoubleButton(
            leftButtonText = "編集",
            rightButtonText = "削除",
            onClickLeftButton = { onEvent(TaskDetailEvent.OnEditTaskEvent(uid = uid)) },
            onClickRightButton = { onEvent(TaskDetailEvent.OnDeleteExecute) },
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDetailItem_Preview() {
    TaskDetailItem(
        uid = 1,
        title = "Room学習",
        comment = "Dao作成\nQuery登録",
        progressPercent = 0.3f,
        targetDate = "2026/4/28",
        onEvent = {},
    )
}