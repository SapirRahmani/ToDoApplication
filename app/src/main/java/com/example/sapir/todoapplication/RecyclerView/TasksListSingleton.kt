package com.example.sapir.todoapplication.RecyclerView

import com.example.sapir.todoapplication.Task

class TasksListSingleton() {

    companion object {
        private lateinit var INSTANCE: TasksListSingleton

        val instance: TasksListSingleton get() = INSTANCE
        private val M_TASKS_LIST: ArrayList<Task> = ArrayList<Task>()

        fun initialize() {
            INSTANCE =
                    TasksListSingleton()
        }

        fun deleteMany(tasks: ArrayList<Task>) {
            M_TASKS_LIST.removeAll(tasks)
        }

        fun delete(task: Task) {
            M_TASKS_LIST.remove(task)
        }

        fun size(): Int {
            return M_TASKS_LIST.size
        }

        fun add(task: Task) {
            M_TASKS_LIST.add(task)
        }

        fun edit(oldTask: Task, newTask: Task) {
            val index = M_TASKS_LIST.indexOf(oldTask)
            if (index != -1) {
                M_TASKS_LIST[index] = newTask
            }
        }

        fun getTaskByPos(position: Int): Task {
            return M_TASKS_LIST[position]
        }

        fun getTasksList(): ArrayList<Task> {
            return M_TASKS_LIST
        }

    }


}
