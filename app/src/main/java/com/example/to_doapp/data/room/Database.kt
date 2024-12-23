package com.example.to_doapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.to_doapp.data.entity.Tasks

@Database(entities = [Tasks::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun getTasksDao() : TasksDao
}