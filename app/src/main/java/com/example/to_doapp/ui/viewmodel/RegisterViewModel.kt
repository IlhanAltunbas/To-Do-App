package com.example.to_doapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_doapp.data.repository.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RegisterViewModel @Inject constructor(var tRepo: TasksRepository):ViewModel() {

    fun save(task_name: String) {
        viewModelScope.launch {
            tRepo.save(task_name)
        }
    }
}