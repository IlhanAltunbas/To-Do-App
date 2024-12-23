package com.example.to_doapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_doapp.data.entity.Tasks
import com.example.to_doapp.data.repository.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomePageViewModel @Inject constructor(var tRepo: TasksRepository) : ViewModel() {
    var tasksList = MutableLiveData<List<Tasks>>(emptyList())
    var taskListChecked = MutableLiveData<List<Tasks>>(emptyList())

    init {
        getTasks()
    }

    fun getTasks() {
        viewModelScope.launch {
            try {
                val tempList = tRepo.getTasks()
                updateTasks(tempList)
            } catch (e: Exception) {
                Log.e("TasksViewModel", "Görevleri getirme hatası", e)
            }
        }
    }

    private fun updateTasks(taskList: List<Tasks>) {
        val (checkedTasks, uncheckedTasks) = taskList.partition { it.task_check }

        // If the list really changed then update
        if (tasksList.value != uncheckedTasks) {
            tasksList.value = uncheckedTasks
        }
        if (taskListChecked.value != checkedTasks) {
            taskListChecked.value = checkedTasks
        }
    }

    fun search(searchKey: String) {
        viewModelScope.launch {
            try {
                val results = tRepo.search(searchKey)
                updateTasks(results)
            } catch (e: Exception) {
                Log.e("TasksViewModel", "Görev arama hatası", e)
            }
        }
    }

    fun delete(task_id: Int) {
        viewModelScope.launch {
            try {
                tRepo.delete(task_id)
                getTasks()
            } catch (e: Exception) {
                Log.e("TasksViewModel", "Görev silme hatası", e)
            }
        }
    }

    fun checkUpdate(task_id: Int,task_name:String ,checked: Boolean) {
        viewModelScope.launch {
            try {
                tRepo.checkUpdate(task_id,task_name ,checked)
                getTasks()
            } catch (e: Exception) {
                Log.e("TasksViewModel", "Görev kontrol durumu güncelleme hatası", e)
            }
        }
    }
}
