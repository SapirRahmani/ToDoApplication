package com.example.sapir.todoapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NewTaskFragment : Fragment() {


    companion object {

        fun newInstance(): NewTaskFragment {
            return NewTaskFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_new_task, container, false)
    }

}