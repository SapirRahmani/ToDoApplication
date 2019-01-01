package com.example.sapir.todoapplication

import android.os.Bundle
import android.view.View

interface  NavigationListener {
    fun onNavClick(fragment: String, bundle: Bundle? = null)
}