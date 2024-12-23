package com.example.to_doapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_doapp.data.repository.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(var tRepo: TasksRepository) :ViewModel() {

    fun update(task_id:Int, task_name: String,task_check: Boolean) {
        viewModelScope.launch{
            tRepo.update(task_id,task_name,task_check)
        }
    }
}