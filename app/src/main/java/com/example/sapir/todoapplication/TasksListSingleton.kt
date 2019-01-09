package com.example.sapir.todoapplication

class TasksListSingleton () {
    val  TasksList : ArrayList<ToDoTask>
    init {
        TasksList = ArrayList<ToDoTask>()
    }

    companion object {
        private lateinit var INSTANCE: TasksListSingleton

        val instance: TasksListSingleton get() = INSTANCE

        fun initialize(){
            INSTANCE = TasksListSingleton()
        }

    }
}
