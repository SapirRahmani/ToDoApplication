package com.example.sapir.todoapplication.Room

import android.arch.persistence.room.*
import com.example.sapir.todoapplication.Task

@Dao
abstract interface TaskDao {
    /*@Query("SELECT * from tasks")
    fun getAll(): ArrayList<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Query("DELETE from tasks")
    fun deleteAll()

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)*/
}
