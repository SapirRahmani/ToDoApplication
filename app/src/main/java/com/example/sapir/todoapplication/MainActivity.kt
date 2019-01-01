package com.example.sapir.todoapplication

import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.os.Bundle


class MainActivity : AppCompatActivity(), NavigationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.frameLayout_main, ToDoListTasksFragment.newInstance())
                .addToBackStack(null)
                .commit();
        }
    }

    override fun onNavClick(fragment: String, bundle: Bundle?) {
        when (fragment) {
            ToDoListTasksFragment.toString() -> goToDoListTasksPage(bundle)
            NewTaskFragment.toString() -> goNewTaskPage()
        }
    }

    private fun goNewTaskPage() {
        replaceFragment(R.id.frameLayout_main, NewTaskFragment.newInstance())
    }

    private fun goToDoListTasksPage(bundle: Bundle?) {
        val showInfoFragment = ToDoListTasksFragment.newInstance()
        if (bundle != null) showInfoFragment.arguments = bundle
        replaceFragment(R.id.frameLayout_main, showInfoFragment)
    }

    private fun replaceFragment(layoutId: Int, fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(layoutId, fragment)
            .addToBackStack(null)
            .commit();
    }
}
