package com.example.sapir.todoapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sapir.todoapplication.databinding.FragmentNewTaskBinding
import kotlinx.android.synthetic.main.fragment_new_task.*

class NewTaskFragment : BaseFragment() {

    private var fragmentAddNewTaskBinding: FragmentNewTaskBinding? = null

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

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val editTask = arguments?.get(getString(R.string.edit_task_bundle_key))
        if (editTask != null)
            fragmentAddNewTaskBinding?.task = editTask as ToDoTask?


        var args = Bundle()
        fab_save_task.setOnClickListener {
            args = saveTask()
            myListener.onNavClick(TasksListFragment.toString(), args)
        }
    }


}