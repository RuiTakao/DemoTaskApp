package com.takaobrog.roomcompose.presentation.task_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.roomcompose.domain.use_case.GetTaskListUseCase
import com.takaobrog.roomcompose.presentation.task_list.ui_model.TaskListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val useCase: GetTaskListUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<TaskListUiState>(TaskListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            useCase()
                .catch { e ->
                    Log.d("TaskListViewModel", "error: " + e.message)
                    _uiState.value = TaskListUiState.Error(e.message ?: "")
                }
                .collect {
                    Log.d("TaskListViewModel", "success: " + it.size)
                    _uiState.value = TaskListUiState.Success(it)
                }
        }
    }
}