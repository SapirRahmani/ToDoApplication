package com.example.sapir.todoapplication.Room

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.example.sapir.todoapplication.Task
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: LiveData<List<Task>> = taskDao.getAll()

    @WorkerThread
    fun insert(task: Task) {
        ExecutorWrapper.wrapperFunOneTask(taskDao::insert,task)
    }

    @WorkerThread
    fun insertMany(tasks: List<Task>) {
        ExecutorWrapper.wrapperFunTaskList(taskDao::insertMany,tasks)
    }

    @WorkerThread
    fun delete(task: Task) {
        ExecutorWrapper.wrapperFunOneTask(taskDao::delete,task)
    }

    @WorkerThread
    fun deleteMany(dates: List<Date>) {
        ExecutorWrapper.wrapperFunLongList(taskDao::deleteMany,dates.map { it.time })
    }

    @WorkerThread
    fun update(task: Task) {
        ExecutorWrapper.wrapperFunOneTask(taskDao::update,task)
    }
}