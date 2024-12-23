package com.example.to_doapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "tasks")
data class Tasks(@PrimaryKey(autoGenerate = true)
                 @ColumnInfo("task_id") @NotNull var task_id: Int,
                 @ColumnInfo("task_name") @NotNull var task_name:String,
                 @ColumnInfo("task_check") @NotNull var task_check:Boolean) :Serializable {
}