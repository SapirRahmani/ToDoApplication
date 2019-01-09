package com.example.sapir.todoapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sapir.todoapplication.databinding.FragmentNewTaskBinding
import kotlinx.android.synthetic.main.fragment_new_task.*
import java.util.*

class NewTaskFragment : BaseFragment() {

    private var fragmentAddNewTaskBinding: FragmentNewTaskBinding? = null
    private var editMode = false

    companion object {

        fun newInstance(): NewTaskFragment {
            return NewTaskFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentAddNewTaskBinding = FragmentNewTaskBinding.inflate(
            inflater, container, false
        )
        val toDoTask = ToDoTask(null, "", false)

        fragmentAddNewTaskBinding?.task = toDoTask

        return fragmentAddNewTaskBinding?.root
    }

    private fun saveTask(): Bundle {
        val bundle = Bundle()
        bundle.putParcelable(getString(R.string.new_task_bundle_key), fragmentAddNewTaskBinding?.task)
        return bundle
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTask = arguments?.get(getString(R.string.edit_task_bundle_key))
        // change title and prepare edit mode
        if (editTask != null) {
            editMode = true;
            tv_newTaskTitle.text = getString(R.string.editTaskTitle)
            fragmentAddNewTaskBinding?.task = editTask as ToDoTask?
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
                var args = saveTask()
                myListener.onNavClick(TasksListFragment.toString(), args)
            }
        }
    }
}