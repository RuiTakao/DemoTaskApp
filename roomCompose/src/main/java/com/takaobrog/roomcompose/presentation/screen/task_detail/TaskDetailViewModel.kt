package com.takaobrog.roomcompose.presentation.screen.task_detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.roomcompose.domain.use_case.DeleteTaskDetailUseCase
import com.takaobrog.roomcompose.domain.use_case.GetTaskDetailUseCase
import com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model.TasKDetailUiState
import com.takaobrog.roomcompose.presentation.screen.task_detail.ui_model.TaskDetailEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUseCase: GetTaskDetailUseCase,
    private val deleteUseCase: DeleteTaskDetailUseCase,
) : ViewModel() {
    val uid: Int = savedStateHandle["uid"] ?: 0

    private val _uiState = MutableStateFlow<TasKDetailUiState>(TasKDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<TaskDetailEffect>()
    val effect = _effect.asSharedFlow()

    private val _showDialog = MutableStateFlow<Boolean>(false)
    val showDialog = _showDialog

    init {
        viewModelScope.launch {
            getUseCase(uid = uid)
                .catch { e ->
                    _uiState.value = TasKDetailUiState.Error(message = e.message)
                    Log.e(TAG, "message ${e.message}")
                }
                .collect {
                    _uiState.value = TasKDetailUiState.Success(item = it)
                }
        }
    }

    fun deleteConfirm() {
        _showDialog.value = true
    }

    fun onDismiss() {
        _showDialog.value = false
    }

    fun delete() {
        viewModelScope.launch {
            deleteUseCase(uid = uid)
            _effect.emit(TaskDetailEffect.NavigateBack)
        }
    }

    companion object {
        private val TAG = TaskDetailViewModel.javaClass.name
    }
}