package com.example.sapir.todoapplication.Fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.sapir.todoapplication.Listener.NavigationListener
import com.example.sapir.todoapplication.Room.TaskViewModel

open class BaseFragment : Fragment() {
    lateinit var myListener: NavigationListener
    lateinit var mTaskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is NavigationListener)
            myListener = context


    }
}