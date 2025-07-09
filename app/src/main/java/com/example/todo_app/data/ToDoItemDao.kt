
package com.example.todo_app.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoItemDao {
    @Query("SELECT * FROM todo_items ORDER BY isCompleted ASC, id DESC")
    fun getAll(): Flow<List<ToDoItem>>

    @Insert
    suspend fun insert(item: ToDoItem)

    @Update
    suspend fun update(item: ToDoItem)

    @Delete
    suspend fun delete(item: ToDoItem)
}
