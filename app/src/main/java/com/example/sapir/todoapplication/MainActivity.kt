package com.example.sapir.todoapplication

import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.Menu
import com.example.sapir.todoapplication.sapirrr.TaskItem
import com.example.sapir.todoapplication.sapirrr.TasksListSingleton
import com.example.sapir.todoapplication.Fragment.NewTaskFragment
import com.example.sapir.todoapplication.Fragment.TasksListFragment
import com.example.sapir.todoapplication.Listener.NavigationListener


class MainActivity : AppCompatActivity(), NavigationListener {

    lateinit var tasks: ArrayList<TaskItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TasksListSingleton.initialize()

        if (savedInstanceState == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.frameLayout_main, TasksListFragment.newInstance())
                .addToBackStack(null)
                .commit();
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onNavClick(fragment: String, key: String?, item: TaskItem?) {
        val bundle: Bundle = Bundle()
        if (item != null && key != null) {
            bundle.putParcelable(key, item)
        }

        when (fragment) {
            TasksListFragment.toString() -> goToTasksListPage(bundle)
            NewTaskFragment.toString() -> goToNewTaskPage()
            getString(R.string.edit_fragment) -> goToEditTaskPage(bundle)
        }
    }

    private fun goToEditTaskPage(editTaskBundle: Bundle?) {
        val fragmentInc = NewTaskFragment.newInstance()
        if (editTaskBundle != null) {
            fragmentInc.arguments = editTaskBundle
        }
        replaceFragment(R.id.frameLayout_main, fragmentInc)
    }

    private fun goToNewTaskPage() {
        replaceFragment(R.id.frameLayout_main, NewTaskFragment.newInstance())
    }

    private fun goToTasksListPage(newTaskBundle: Bundle?) {
        val tasksListFragment = TasksListFragment.newInstance()
        if (newTaskBundle != null) {

            // get new task and put in list
            var newTask = newTaskBundle[this.getString(R.string.new_task_bundle_key)] as TaskItem
            TasksListSingleton.add(newTask)
        }
        replaceFragment(R.id.frameLayout_main, tasksListFragment)
    }

    private fun replaceFragment(layoutId: Int, fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(layoutId, fragment)
            .addToBackStack(null)
            .commit();
    }
}
