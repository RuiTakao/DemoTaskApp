package com.takaobrog.roomcompose.presentation.screen.task_create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.roomcompose.domain.use_case.CreateTaskError
import com.takaobrog.roomcompose.domain.use_case.CreateTaskException
import com.takaobrog.roomcompose.domain.use_case.CreateTaskUseCase
import com.takaobrog.roomcompose.presentation.screen.task_create.ui_model.TaskCreateEffect
import com.takaobrog.roomcompose.presentation.screen.task_create.ui_model.TaskCreateFormState
import com.takaobrog.roomcompose.presentation.screen.task_create.ui_model.TaskCreateUiState
import com.takaobrog.roomcompose.util.local_date.TimeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskCreateViewModel @Inject constructor(
    private val createUseCase: CreateTaskUseCase,
    private val timeProvider: TimeProvider,
) : ViewModel() {
    private val _effect = MutableSharedFlow<TaskCreateEffect>()
    val effect = _effect.asSharedFlow()

    private val _uiState = MutableStateFlow(TaskCreateUiState(formState = TaskCreateFormState()))
    val uiState = _uiState.asStateFlow()

    fun inputTitle(title: String) {
        _uiState.update { state ->
            state.copy(
                formState = state.formState.copy(title = title)
            )
        }
    }

    fun inputComment(comment: String) {
        _uiState.update { state ->
            state.copy(
                formState = state.formState.copy(comment = comment)
            )
        }
    }

    fun inputTargetDate(targetDate: Long?) {
        val formatTargetDate = targetDate?.let { timeProvider.formatterYmd(it) } ?: ""
        _uiState.update { state ->
            state.copy(
                formState = state.formState.copy(
                    targetDate = targetDate,
                    formatTargetDate = formatTargetDate,
                )
            )
        }
    }

    fun submit() {
        viewModelScope.launch {
            createUseCase(
                title = _uiState.value.formState.title,
                comment = _uiState.value.formState.comment,
                targetDate = _uiState.value.formState.targetDate,
            ).fold(onSuccess = {
                _effect.emit(TaskCreateEffect.NavigateBack)
            }, onFailure = { e ->
                val message = if (e is CreateTaskException) {
                    when (val message = e.error) {
                        CreateTaskError.TitleEmpty -> "タイトルが入力されていません"

                        is CreateTaskError.TitleOver -> "タイトルは${message.length}文字以下で入力してください"

                        is CreateTaskError.CommentOver -> "コメントは${message.length}文字以下で入力してください"
                    }
                } else {
                    e.message
                }
                _uiState.update {
                    it.copy(errorMessage = message)
                }
            })
        }
    }

    fun onDismiss() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }
}