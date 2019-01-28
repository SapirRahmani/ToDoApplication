package com.example.sapir.todoapplication.Room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.sapir.todoapplication.Task
import java.util.*

@Dao
interface TaskDao {
    @Query("SELECT * from tasks")
    fun getAll(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)
    @Delete
    fun delete(task: Task)

    @Query("DELETE from tasks where createDate in (:ids)")
    fun deleteMany(ids: List<Int>)

    @Update
    fun update(task: Task)
}
