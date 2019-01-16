package com.example.sapir.todoapplication.Room

import android.arch.lifecycle.LiveData
import android.databinding.adapters.Converters
import android.support.annotation.WorkerThread
import com.example.sapir.todoapplication.Task
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: LiveData<List<Task>> = taskDao.getAll()

    @WorkerThread
    fun insert(task: Task) {
        var myExecutor: Executor = Executors.newSingleThreadExecutor();
        myExecutor.execute {
            taskDao.insert(task)
        }
    }

    @WorkerThread
    fun getById(date: Date) {
        var myExecutor: Executor = Executors.newSingleThreadExecutor();
        myExecutor.execute {
            taskDao.getById(date)
        }
    }

    @WorkerThread
    fun insertAll(tasks: List<Task>) {
        var myExecutor: Executor = Executors.newSingleThreadExecutor();
        myExecutor.execute {
            taskDao.insertAll(tasks)
        }
    }

    @WorkerThread
    fun delete(task: Task) {
        var myExecutor: Executor = Executors.newSingleThreadExecutor();
        myExecutor.execute {
            taskDao.delete(task)
        }
    }

    @WorkerThread
    fun deleteMany(dates: List<Date>) {
        var myExecutor: Executor = Executors.newSingleThreadExecutor();
        myExecutor.execute {
            taskDao.deleteMany(dates.map { it.time })
        }
    }

    @WorkerThread
    fun update(task: Task) {
        var myExecutor: Executor = Executors.newSingleThreadExecutor();
        myExecutor.execute {
            taskDao.update(task)
        }
    }
}