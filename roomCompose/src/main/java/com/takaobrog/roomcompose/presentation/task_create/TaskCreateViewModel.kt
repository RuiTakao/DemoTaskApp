package com.takaobrog.roomcompose.presentation.task_create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.roomcompose.domain.use_case.CreateTaskUseCase
import com.takaobrog.roomcompose.presentation.task_create.ui_model.TaskCreateEffect
import com.takaobrog.roomcompose.util.local_date.TimeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskCreateViewModel @Inject constructor(
    private val createUseCase: CreateTaskUseCase,
    private val timeProvider: TimeProvider,
) : ViewModel() {
    private val _effect = MutableSharedFlow<TaskCreateEffect>()
    val effect = _effect.asSharedFlow()

    fun submit(name: String, progressPercent: Float, targetDate: String?) {
        viewModelScope.launch {
            createUseCase(name = name, progressPercent = progressPercent, targetDate = targetDate)
            _effect.emit(TaskCreateEffect.NavigateBack)
        }
    }

    fun longToLocalDate(targetDate: Long): String = timeProvider.longToLocalDate(targetDate)

    fun formatToTargetDate(targetDate: String): String? = timeProvider.formatterYmd(targetDate)
}