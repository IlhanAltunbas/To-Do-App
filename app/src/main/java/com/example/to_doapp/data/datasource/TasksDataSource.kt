package com.example.to_doapp.data.datasource

import android.util.Log
import com.example.to_doapp.data.entity.Tasks
import com.example.to_doapp.data.room.TasksDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TasksDataSource(var tdao: TasksDao) {



    suspend fun getTasks() : List<Tasks> =
        withContext(Dispatchers.IO){
            return@withContext tdao.getTasks()
        }

    suspend fun search(searchKey: String): List<Tasks> =
        withContext(Dispatchers.IO){
            return@withContext tdao.search(searchKey)
        }


    suspend fun update(task_id:Int, task_name: String, task_check:Boolean) {
        val updatedObject = Tasks(task_id,task_name,task_check)
        tdao.update(updatedObject)
    }

    suspend fun save(task_name: String) {
        val newObject = Tasks(0,task_name,false)
        tdao.save(newObject)
    }
    suspend fun delete(task_id: Int){
        val deletedObject = Tasks(task_id,"",false)
        tdao.delete(deletedObject)
    }
    suspend fun checkUpdate(task_id: Int,task_name: String,task_check: Boolean) {
        val updatedCheckObject = Tasks(task_id,task_name,task_check)
        tdao.update(updatedCheckObject)
    }

}