
package com.example.todo_app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todo_app.data.ToDoDatabase
import com.example.todo_app.data.ToDoItem
import com.example.todo_app.data.ToDoItemDao
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application) {
    private val todoItemDao: ToDoItemDao = ToDoDatabase.getDatabase(application).todoItemDao()

    val allItems: LiveData<List<ToDoItem>> = todoItemDao.getAll().asLiveData()

    fun insert(item: ToDoItem) = viewModelScope.launch {
        todoItemDao.insert(item)
    }

    fun update(item: ToDoItem) = viewModelScope.launch {
        todoItemDao.update(item)
    }

    fun delete(item: ToDoItem) = viewModelScope.launch {
        todoItemDao.delete(item)
    }
}
