package com.example.sapir.todoapplication.Listener

import com.example.sapir.todoapplication.sapirrr.TaskItem

interface  NavigationListener {
    fun onNavClick(fragment: String, key:String?, task: TaskItem? = null)
}