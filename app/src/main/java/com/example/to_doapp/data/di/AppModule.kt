package com.example.to_doapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.to_doapp.data.datasource.TasksDataSource
import com.example.to_doapp.data.repository.TasksRepository
import com.example.to_doapp.data.room.Database
import com.example.to_doapp.data.room.TasksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideTasksDataSource(tdao: TasksDao) : TasksDataSource {
        return TasksDataSource(tdao)
    }
    @Provides
    @Singleton
    fun provideTasksRepository(tds:TasksDataSource) : TasksRepository {
        return TasksRepository(tds)
    }
    @Provides
    @Singleton
    fun provideTasksDao(@ApplicationContext context: Context) : TasksDao {
        val db = Room.databaseBuilder(context,Database::class.java,"tasks.sqlite")
            .createFromAsset("tasks.sqlite").build()
        return db.getTasksDao()
    }

}