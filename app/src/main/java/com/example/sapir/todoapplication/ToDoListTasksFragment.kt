package com.example.sapir.todoapplication

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_to_do_list_tasks.*
import android.support.v7.widget.DividerItemDecoration
import com.example.sapir.todoapplication.databinding.FragmentToDoListTasksBinding
import java.util.*


class ToDoListTasksFragment : BaseFragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var fragmentTodoListTasksBinding: FragmentToDoListTasksBinding? = null


    companion object {

        fun newInstance(): ToDoListTasksFragment {
            return ToDoListTasksFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentTodoListTasksBinding = FragmentToDoListTasksBinding.inflate(
            inflater, container, false
        )
        val emptyTask = ToDoTask(null, "", false)

        fragmentTodoListTasksBinding?.task = emptyTask
        return fragmentTodoListTasksBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val mRecyclerView = rv_todo_list as RecyclerView

        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        mRecyclerView.addItemDecoration(itemDecoration)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)

        val items = ArrayList<ToDoTask>()
        items.add(ToDoTask("Go to market", "Buy cola"))
        items.add(ToDoTask("Go to market", "Buy eggs"))
        items.add(ToDoTask("Go to market", "Buy pie"))
        items.add(ToDoTask("Go to market", "Buy milk"))
        items.add(ToDoTask("Go to market", "Buy cola"))
        items.add(ToDoTask("Go to market", "Buy eggs"))
        items.add(ToDoTask("Go to market", "Buy pie"))
        items.add(ToDoTask("Go to market", "Buy milk"))

        mRecyclerView.adapter = MyAdapter(items)


        fab_add_task?.setOnClickListener { myListener.onNavClick(NewTaskFragment.toString(), null) }

    }
}

