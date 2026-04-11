package com.takaobrog.roomcompose.presentation.task_create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.roomcompose.domain.use_case.CreateTaskUseCase
import com.takaobrog.roomcompose.presentation.task_create.ui_model.TaskCreateEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskCreateViewModel @Inject constructor(
    private val createUseCase: CreateTaskUseCase
) : ViewModel() {
    private val _effect = MutableSharedFlow<TaskCreateEffect>()
    val effect = _effect.asSharedFlow()

    fun submit(name: String, progressPercent: Float, targetDate: String?) {
        viewModelScope.launch {
            createUseCase(name = name, progressPercent = progressPercent, targetDate = targetDate)
            _effect.emit(TaskCreateEffect.NavigateBack)
        }
    }
}