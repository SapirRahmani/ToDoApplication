package com.example.sapir.todoapplication

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.Menu
import com.example.sapir.todoapplication.Fragment.NewTaskFragment
import com.example.sapir.todoapplication.Fragment.TasksListFragment
import com.example.sapir.todoapplication.Listener.NavigationListener
import com.example.sapir.todoapplication.Listener.BackPressedListener
import com.example.sapir.todoapplication.Room.TaskViewModel


class MainActivity : AppCompatActivity(), NavigationListener {

    lateinit var mTaskViewModel: TaskViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)

        if (savedInstanceState == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.frameLayout_main, TasksListFragment.newInstance())
                .addToBackStack(null)
                .commit();
        }
    }

    override fun onBackPressed() {
        val fragmentList = supportFragmentManager.fragments
        for (fragment: Fragment in fragmentList) {
            if (fragment is BackPressedListener) {
                (fragment as BackPressedListener).onBackPressed();
                super.onBackPressed()
            }
        }
        super.onBackPressed()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onNavClick(fragment: String) {
        when (fragment) {
            TasksListFragment.toString() -> replaceFragment(R.id.frameLayout_main, TasksListFragment.newInstance())
            NewTaskFragment.toString() -> replaceFragment(R.id.frameLayout_main, NewTaskFragment.newInstance())
            getString(R.string.edit_fragment) -> replaceFragment(R.id.frameLayout_main, NewTaskFragment.newInstance())
        }
    }

    private fun replaceFragment(layoutId: Int, fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(layoutId, fragment)
            .addToBackStack(null)
            .commit()
    }
}
