package com.takaobrog.roomcompose.presentation.screen.task_detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.roomcompose.domain.repository.TaskRepository
import com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model.TasKDetailUiState
import com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model.TaskDetailEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: TaskRepository,
) : ViewModel() {
    val uid: Int = savedStateHandle["uid"] ?: 0

    private val _uiState = MutableStateFlow<TasKDetailUiState>(TasKDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<TaskDetailEffect>()
    val effect = _effect.asSharedFlow()

    init {
        viewModelScope.launch {
            repository.getTask(uid = uid).fold(
                onSuccess = {
                    it?.let {
                        _uiState.value = TasKDetailUiState.Success(it)
                    }
                },
                onFailure = { e->
                    Log.e(TAG, "getTask failed", e)
                    _uiState.value = TasKDetailUiState.Error(e.message)
                }
            )
        }
    }

    fun delete() {
        viewModelScope.launch {
            repository.delete(uid = uid)
            _effect.emit(TaskDetailEffect.NavigateBack)
        }
    }

    companion object {
        private val TAG = TaskDetailViewModel.javaClass.name
    }
}