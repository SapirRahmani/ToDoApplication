package com.example.sapir.todoapplication.Room

import android.arch.lifecycle.LiveData
import com.example.sapir.todoapplication.Task
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.reflect.KFunction1

object ExecutorWrapper {

    @JvmStatic
    fun wrapperFunOneTask(
        method: KFunction1<@ParameterName(name = "task") Task, Unit>,
        obj: @ParameterName(name = "task") Task
    ) {
        val myExecutor: Executor = Executors.newSingleThreadExecutor()
        myExecutor.execute {
            method(obj)
        }
    }

    @JvmStatic
    fun wrapperFunLongList(
        method: KFunction1<@ParameterName(name = "task") List<Long>, Unit>,
        obj: @ParameterName(name = "task") List<Long>
    ) {
        val myExecutor: Executor = Executors.newSingleThreadExecutor()
        myExecutor.execute {
            method(obj)
        }
    }

    @JvmStatic
    fun wrapperFunTaskList(
        method: KFunction1<@ParameterName(name = "task") List<Task>, Unit>,
        obj: @ParameterName(name = "task") List<Task>
    ) {
        val myExecutor: Executor = Executors.newSingleThreadExecutor()
        myExecutor.execute {
            method(obj)
        }
    }
}