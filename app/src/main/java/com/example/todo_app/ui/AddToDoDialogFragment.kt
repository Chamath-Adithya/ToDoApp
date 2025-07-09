
package com.example.todo_app.ui

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.todo_app.R
import com.example.todo_app.data.ToDoItem

class AddToDoDialogFragment : DialogFragment() {

    private val viewModel: ToDoViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext()).apply {
            setTitle("Add New ToDo")
            val view = layoutInflater.inflate(R.layout.dialog_add_todo, null)
            val todoEditText = view.findViewById<EditText>(R.id.todoEditText)
            setView(view)
            setPositiveButton("Add") {
                dialog, _ ->
                val todoTitle = todoEditText.text.toString()
                if (todoTitle.isNotBlank()) {
                    viewModel.insert(ToDoItem(title = todoTitle))
                }
                dialog.dismiss()
            }
            setNegativeButton("Cancel") {
                dialog, _ ->
                dialog.cancel()
            }
        }.create()
    }
}
