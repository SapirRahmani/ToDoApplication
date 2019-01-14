package com.example.sapir.todoapplication.Room

import android.arch.persistence.room.*
import android.content.Context
import com.example.sapir.todoapplication.Task

@Database(entities = [Task::class], version = 1)
abstract class TasksDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private var INSTANCE: TasksDatabase? = null

        fun getInstance(context: Context?): TasksDatabase? {
            if (INSTANCE == null && context != null) {
                synchronized(TasksDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        TasksDatabase::class.java, "tasks.db"
                    )
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}