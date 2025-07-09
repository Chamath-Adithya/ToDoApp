
package com.example.todo_app.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app.data.ToDoItem
import com.example.todo_app.databinding.ItemTodoBinding

class ToDoAdapter(
    private val onCheckboxClicked: (ToDoItem) -> Unit,
    private val onDeleteClicked: (ToDoItem) -> Unit
) : ListAdapter<ToDoItem, ToDoAdapter.ToDoViewHolder>(ToDoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
    ToDoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ToDoViewHolder(private val binding: ItemTodoBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ToDoItem) {
            binding.titleTextView.text = item.title
            binding.completedCheckBox.isChecked = item.isCompleted
            updateStrikeThrough(binding.titleTextView, item.isCompleted)

            binding.completedCheckBox.setOnClickListener {
                onCheckboxClicked(item.copy(isCompleted = !item.isCompleted))
            }

            binding.deleteButton.setOnClickListener {
                onDeleteClicked(item)
            }
        }

        private fun updateStrikeThrough(textView: TextView, isCompleted: Boolean) {
            if (isCompleted) {
                textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
    }
}

class ToDoDiffCallback : DiffUtil.ItemCallback<ToDoItem>() {
    override fun areItemsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
        return oldItem == newItem
    }
}
