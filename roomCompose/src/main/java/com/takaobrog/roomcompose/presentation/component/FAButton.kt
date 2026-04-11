package com.takaobrog.roomcompose.presentation.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.roomcompose.R

@Composable
fun FAButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = stringResource(id = R.string.fab_icon_description),
                tint = colorResource(id = R.color.fab_icon_color),
                modifier = Modifier.size(dimensionResource(id = R.dimen.fab_icon_size))
            )
        }
    )
}

@Preview
@Composable
fun FAButtonPreview() {
    FAButton(onClick = {})
}