package com.example.sapir.todoapplication.Room

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.sapir.todoapplication.Task
import java.util.*

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository
    val allTasks: LiveData<List<Task>>
    var editTask :Task? = null

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository.getRepository(taskDao)
        allTasks = repository.getAll()
    }

    fun insert(task: Task) {
        repository.insert(task)
    }

    fun delete(task: Task) {
        repository.delete(task)
    }

    fun deleteMany(tasks: List<Task>) {
        repository.deleteMany(tasks)
    }

    fun update(task: Task) {
        repository.update(task)
    }
}