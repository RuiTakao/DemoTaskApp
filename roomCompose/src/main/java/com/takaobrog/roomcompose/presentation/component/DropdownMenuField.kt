package com.takaobrog.roomcompose.presentation.component

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

enum class ProgressPercentStatus(val label: String, val data: Float) {
    ZERO("0%", 0.0f),
    ONE("10%", 0.1f),
    TWO("20%", 0.2f),
    THREE("30%", 0.3f),
    FOUR("40%", 0.4f),
    FIVE("50%", 0.5f),
    SIX("60%", 0.6f),
    SEVEN("70%", 0.7f),
    EIGHT("80%", 0.8f),
    NINE("90%", 0.9f),
    MAX("100%", 1.0f),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuField(
    label: String,
    value: String,
    onValueChange: (ProgressPercentStatus) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier,
    ) {
        DefaultTextField(
            label = label,
            value = value,
            modifier = Modifier.menuAnchor(
                type = MenuAnchorType.PrimaryEditable,
                enabled = true
            ),
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            ProgressPercentStatus.entries.forEach {
                DropdownMenuItem(
                    text = { Text(it.label) },
                    onClick = {
                        onValueChange(it)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun DropdownMenuField_Preview() {
    DropdownMenuField(
        label = "進捗",
        value = ProgressPercentStatus.MAX.label,
        onValueChange = {},
    )
}