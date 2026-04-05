package com.takaobrog.roomcompose.presentation.task_list

import androidx.lifecycle.ViewModel
import com.takaobrog.roomcompose.domain.use_case.GetTaskListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    getTaskListUseCase: GetTaskListUseCase,
) : ViewModel() {
    val taskList = getTaskListUseCase()
}