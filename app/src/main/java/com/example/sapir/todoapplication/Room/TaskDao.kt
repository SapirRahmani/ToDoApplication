package com.example.sapir.todoapplication.Room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.sapir.todoapplication.Task
import java.util.*

@Dao
abstract interface TaskDao {
    @Query("SELECT * from tasks")
    fun getAll(): LiveData<ArrayList<Task>>

    @Query("SELECT * from tasks WHERE createDate == :date")
    fun getById(date:Date): LiveData<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Insert
    abstract fun insertAll(tasks: ArrayList<Task>)

    @Delete
    fun delete(task: Task)

    @Query("DELETE from tasks")
    fun deleteAll()

    @Update
    fun update(task: Task)
}
