package com.example.sapir.todoapplication.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sapir.todoapplication.Listener.BackPressedListener
import com.example.sapir.todoapplication.R
import com.example.sapir.todoapplication.Task
import com.example.sapir.todoapplication.databinding.FragmentNewTaskBinding
import kotlinx.android.synthetic.main.fragment_new_task.*
import java.util.*

class NewTaskFragment : BaseFragment(), BackPressedListener {


    private var fragmentAddNewTaskBinding: FragmentNewTaskBinding? = null
    private var editMode = false
    private var oldTask: Task? = null

    companion object {

        fun newInstance(): NewTaskFragment {
            return NewTaskFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentAddNewTaskBinding = FragmentNewTaskBinding.inflate(
            inflater, container, false
        )

        fragmentAddNewTaskBinding?.task = Task()

        return fragmentAddNewTaskBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTask = mTaskViewModel.editTask
        // change title and prepare edit mode
        if (editTask != null) {
            oldTask = editTask
            editMode = true
            tv_newTaskTitle.text = getString(R.string.editTaskTitle)
            fragmentAddNewTaskBinding?.task = editTask
            fragmentAddNewTaskBinding?.task?.createDate = Date()

        } else {
            tv_newTaskTitle.text = getString(R.string.addTaskTitle)
        }

        fab_save_task.setOnClickListener {
            // validation
            if (et_description.text.isEmpty()) {
                Toast.makeText(activity, getString(R.string.description_is_empty), Toast.LENGTH_SHORT).show()
            } else {
                // save and replace fragment
                if (editTask != null)
                    mTaskViewModel.update(fragmentAddNewTaskBinding?.task!!)
                else
                    mTaskViewModel.insert(fragmentAddNewTaskBinding?.task!!)
                myListener.onNavClick(
                    TasksListFragment.toString()
                )
            }
        }
    }

    override fun onBackPressed() {
        if (oldTask != null) {
            mTaskViewModel.update(oldTask!!)
            // save old task and back to tasks list
            myListener.onNavClick(TasksListFragment.toString())
        }
    }

}