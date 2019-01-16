package com.example.sapir.todoapplication.Room

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.sapir.todoapplication.Task
import java.util.*

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository
    val allTasks: LiveData<List<Task>>

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

    fun insertAll(tasks: List<Task>) {
        repository.insertAll(tasks)
    }

    fun delete(task: Task) {
        repository.delete(task)
    }

    fun deleteMany(dates: List<Date>) {
        repository.deleteMany(dates)
    }

    fun update(task: Task) {
        repository.update(task)
    }
}