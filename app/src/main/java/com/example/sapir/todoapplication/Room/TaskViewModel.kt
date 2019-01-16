package com.example.sapir.todoapplication.Room

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.sapir.todoapplication.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.util.*
import kotlin.coroutines.CoroutineContext

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository
    val allTasks: LiveData<ArrayList<Task>>

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        allTasks = repository.allTasks
    }

    fun getById(date: Date) {
        return repository.getById(date)
    }

    fun insert(task: Task) {
        repository.insert(task)
    }

    fun insertAll(tasks: ArrayList<Task>) {
        repository.insertAll(tasks)
    }

    fun delete(task: Task) {
        repository.delete(task)
    }

    fun deleteAll(tasks: ArrayList<Task>) {
        repository.deleteAll(tasks)
    }

    fun update(task: Task) {
        repository.update(task)
    }
}