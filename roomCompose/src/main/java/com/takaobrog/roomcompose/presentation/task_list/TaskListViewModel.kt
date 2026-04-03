package com.takaobrog.roomcompose.presentation.task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.roomcompose.domain.use_case.CreateTaskUseCase
import com.takaobrog.roomcompose.domain.use_case.GetTaskListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    getTaskListUseCase: GetTaskListUseCase,
    private val createUseCase: CreateTaskUseCase
) : ViewModel() {
    val taskList = getTaskListUseCase()

    fun insert(name: String, detail: String) {
        viewModelScope.launch {
            createUseCase(name = name, detail = detail)
        }
    }
}