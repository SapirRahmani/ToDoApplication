package com.example.sapir.todoapplication.Listener

import com.example.sapir.todoapplication.Entity.Task

interface  NavigationListener {
    fun onNavClick(fragment: String, key:String?, task: Task? = null)
}