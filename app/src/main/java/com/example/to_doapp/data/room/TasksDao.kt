package com.example.to_doapp.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.to_doapp.data.entity.Tasks

@Dao
interface TasksDao {
    @Query ("SELECT * FROM tasks")
    suspend fun getTasks(): List<Tasks>
    @Insert
    suspend fun save(tasks: Tasks)
    @Update
    suspend fun update(tasks: Tasks)
    @Delete
    suspend fun delete(tasks: Tasks)
    @Query ("SELECT * FROM tasks WHERE task_name like '%' || :searchKey || '%'")
    suspend fun search(searchKey:String): List<Tasks>
    @Update
    suspend fun checkUpdate(task:Tasks)

}