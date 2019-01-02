package com.example.sapir.todoapplication

class TasksListSingleton () {
    val  TasksList : ArrayList<ToDoTask>
    init {
        TasksList = ArrayList<ToDoTask>()
    }

    companion object {
        // do not expose var, it includes getter/setting and makes confusion to other users
        private lateinit var INSTANCE: TasksListSingleton

        // Use `val` and define the getter only
        // kotlin will throw `kotlin.UninitializedPropertyAccessException` if the INSTANCE is not initialized
        val instance: TasksListSingleton get() = INSTANCE

        fun initialize(){
            INSTANCE = TasksListSingleton()
        }

    }
}
