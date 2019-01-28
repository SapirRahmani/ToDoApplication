package com.example.sapir.todoapplication.Room

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.example.sapir.todoapplication.Task
import java.util.concurrent.Executors

class TaskRepository() {

    companion object {

        lateinit var taskDao: TaskDao
        private var repository: TaskRepository? = null

        fun getRepository(dao: TaskDao): TaskRepository {
            val tempInstance = repository
            if (tempInstance != null) return tempInstance
            taskDao = dao
            val instance = TaskRepository()
            repository = instance
            return instance
        }
    }

    @WorkerThread
    fun getAll(): LiveData<List<Task>> {
        return taskDao.getAll()
    }

    @WorkerThread
    fun insert(task: Task) {
        val myExecutor = Executors.newSingleThreadExecutor()
        myExecutor.execute {
            taskDao.insert(task)
        }
    }

    @WorkerThread
    fun delete(task: Task) {
        val myExecutor = Executors.newSingleThreadExecutor()
        myExecutor.execute {
            taskDao.delete(task)
        }
    }

    @WorkerThread
    fun deleteMany(tasks: List<Task>) {
        val myExecutor = Executors.newSingleThreadExecutor()
        myExecutor.execute {
            taskDao.deleteMany(tasks.map { it.id })
        }
    }

    @WorkerThread
    fun update(task: Task) {
        val myExecutor = Executors.newSingleThreadExecutor()
        myExecutor.execute {
            taskDao.update(task)
        }
    }
}