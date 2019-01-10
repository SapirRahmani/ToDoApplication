package com.example.sapir.todoapplication.Fragment

import android.content.Context
import android.support.v4.app.Fragment
import com.example.sapir.todoapplication.Listener.NavigationListener

open class BaseFragment  : Fragment(){
    lateinit var myListener: NavigationListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is NavigationListener)
            myListener = context
    }
}