package com.takaobrog.roomcompose.presentation.task_edit

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.roomcompose.domain.repository.TaskRepository
import com.takaobrog.roomcompose.presentation.task_edit.ui_model.TaskEditEffect
import com.takaobrog.roomcompose.presentation.task_edit.ui_model.TaskEditUiState
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
    repository: TaskRepository,
) : ViewModel() {
    val uid: Int = savedStateHandle["uid"] ?: 0

    private val _uiState = MutableStateFlow<TaskEditUiState>(TaskEditUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<TaskEditEffect>()
    val effect = _effect.asSharedFlow()

    init {
        viewModelScope.launch {
            repository.getTask(uid = uid)
                .collect {
                    _uiState.value = TaskEditUiState.Success(it)
                }
        }
    }

    fun delete() {
        viewModelScope.launch {
            Log.d("TaskEditViewModel", "uid $uid")
            _effect.emit(TaskEditEffect.NavigateBack)
        }
    }
}