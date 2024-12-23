package com.example.to_doapp.data.repository

import android.util.Log
import com.example.to_doapp.data.datasource.TasksDataSource
import com.example.to_doapp.data.entity.Tasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TasksRepository(var tds : TasksDataSource) {


    suspend fun getTasks() : List<Tasks> = tds.getTasks()

    suspend fun update(task_id:Int, task_name: String, task_check: Boolean) {
        tds.update(task_id,task_name,task_check)
    }

    suspend fun search(searchKey: String): List<Tasks> = tds.search(searchKey)


    suspend fun save(task_name: String) {
        tds.save(task_name)
    }
    suspend fun delete(task_id: Int) {
        tds.delete(task_id)
    }
    suspend fun checkUpdate(task_id: Int,task_name: String,checked: Boolean) {
        tds.checkUpdate(task_id,task_name,checked)
    }

}