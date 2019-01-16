package com.example.sapir.todoapplication.Room

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.example.sapir.todoapplication.Task
import java.util.*

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: LiveData<ArrayList<Task>> = taskDao.getAll()

    @WorkerThread
    fun insert(task: Task) {
        taskDao.insert(task)
    }

    @WorkerThread
    fun getById(date: Date) {
        taskDao.getById(date)
    }

    @WorkerThread
    fun insertAll(tasks: ArrayList<Task>) {
        taskDao.insertAll(tasks)
    }

    @WorkerThread
    fun delete(task: Task) {
        taskDao.delete(task)
    }

    @WorkerThread
    fun deleteAll(tasks: ArrayList<Task>) {
        taskDao.insertAll(tasks)
    }

    @WorkerThread
    fun update(task: Task) {
        taskDao.update(task)
    }
}