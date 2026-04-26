package com.takaobrog.roomcompose.presentation.screen.task_create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.roomcompose.domain.use_case.CreateTaskUseCase
import com.takaobrog.roomcompose.presentation.component.ProgressPercentStatus
import com.takaobrog.roomcompose.presentation.screen.task_create.ui_model.TaskCreateEffect
import com.takaobrog.roomcompose.presentation.screen.task_create.ui_model.TaskCreateFormState
import com.takaobrog.roomcompose.util.local_date.TimeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskCreateViewModel @Inject constructor(
    private val createUseCase: CreateTaskUseCase,
    private val timeProvider: TimeProvider,
) : ViewModel() {
    private val _effect = MutableSharedFlow<TaskCreateEffect>()
    val effect = _effect.asSharedFlow()

    private val _formState = MutableStateFlow(TaskCreateFormState())
    val formState = _formState.asStateFlow()

    fun inputTitle(title: String) {
        _formState.value = _formState.value.copy(title = title)
    }

    fun inputComment(comment: String) {
        _formState.value = _formState.value.copy(comment = comment)
    }

    fun inputProgressPercent(progressPercent: ProgressPercentStatus) {
        _formState.value = _formState.value.copy(progressPercent = progressPercent)
    }

    fun inputTargetDate(targetDate: Long?) {
        val formatTargetDate = targetDate?.let { timeProvider.formatterYmd(it) } ?: ""
        _formState.value = _formState.value.copy(targetDate = targetDate)
        _formState.value = _formState.value.copy(formatTargetDate = formatTargetDate)
    }

    fun submit() {
        viewModelScope.launch {
            createUseCase(
                title = _formState.value.title,
                comment = _formState.value.comment,
                progressPercent = _formState.value.progressPercent.data,
                targetDate = _formState.value.targetDate,
            )
            _effect.emit(TaskCreateEffect.NavigateBack)
        }
    }
}