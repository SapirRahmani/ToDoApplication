package com.example.sapir.todoapplication.Room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.sapir.todoapplication.Task
import java.util.*

@Dao
interface TaskDao {
    @Query("SELECT * from tasks")
    fun getAll(): LiveData<List<Task>>

    @Query("SELECT * from tasks WHERE createDate == :date")
    fun getById(date: Date): LiveData<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Insert
    fun insertMany(tasks: List<Task>)

    @Delete
    fun delete(task: Task)

    @Query("DELETE from tasks where createDate in (:dates)")
    fun deleteMany(dates: List<Long>)

    @Update
    fun update(task: Task)
}
