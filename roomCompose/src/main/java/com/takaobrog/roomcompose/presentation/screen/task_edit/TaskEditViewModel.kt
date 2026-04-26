package com.takaobrog.roomcompose.presentation.screen.task_edit

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.roomcompose.domain.use_case.GetTaskEditUseCase
import com.takaobrog.roomcompose.domain.use_case.UpdateTaskEditUseCase
import com.takaobrog.roomcompose.presentation.component.ProgressPercentStatus
import com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model.TaskEditEffect
import com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model.TaskEditFormState
import com.takaobrog.roomcompose.presentation.screen.task_edit.ui_model.TaskEditUiState
import com.takaobrog.roomcompose.util.local_date.TimeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUseCase: GetTaskEditUseCase,
    private val updateUseCase: UpdateTaskEditUseCase,
    private val timeProvider: TimeProvider,
) : ViewModel() {
    val uid: Int = savedStateHandle["uid"] ?: 0

    private val _effect = MutableSharedFlow<TaskEditEffect>()
    val effect = _effect.asSharedFlow()

    private val _uiState = MutableStateFlow<TaskEditUiState>(TaskEditUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _formState = MutableStateFlow(TaskEditFormState())
    val formState = _formState.asStateFlow()

    init {
        viewModelScope.launch {
            getUseCase(uid = uid).fold(
                onSuccess = {
                    val progressPercent = ProgressPercentStatus.formData(value = it.progressPercent)
                    _formState.value = TaskEditFormState(
                        title = it.title,
                        comment = it.comment,
                        progressPercent = progressPercent ?: ProgressPercentStatus.ZERO,
                        targetDate = it.targetDate,
                        formatTargetDate = formatTargetDate(targetDate = it.targetDate),
                    )
                },
                onFailure = { e ->
                    Log.e(TAG, "getTaskEdit failed", e)
                    _uiState.value = TaskEditUiState.Error(e.message)
                }
            )
        }
    }

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
        _formState.value = _formState.value.copy(targetDate = targetDate)
        _formState.value =
            _formState.value.copy(formatTargetDate = formatTargetDate(targetDate = targetDate))
    }

    fun submit() {
        viewModelScope.launch {
            updateUseCase(
                uid = uid,
                title = _formState.value.title,
                comment = _formState.value.comment,
                progressPercent = _formState.value.progressPercent.data,
                targetDate = _formState.value.targetDate,
            )
            _effect.emit(TaskEditEffect.NavigateBack)
        }
    }

    private fun formatTargetDate(targetDate: Long?) =
        targetDate?.let { timeProvider.formatterYmd(it) } ?: ""

    companion object {
        private val TAG = TaskEditViewModel.javaClass.name
    }
}