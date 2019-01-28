package com.example.sapir.todoapplication.Room

import android.arch.persistence.room.*
import android.content.Context
import com.example.sapir.todoapplication.Converter.Converters
import com.example.sapir.todoapplication.Task

@Database(entities = [Task::class], version = 1)
@TypeConverters(Converters::class)
public abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var database: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            val tempInstance = database
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "tasks"
                ).fallbackToDestructiveMigration().build()
                database = instance
                return instance
            }
        }
    }
}