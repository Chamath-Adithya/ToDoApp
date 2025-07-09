
package com.example.todo_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.todo_app.databinding.ActivityMainBinding
import com.example.todo_app.ui.AddToDoDialogFragment
import com.example.todo_app.ui.fragments.ToDoListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<ToDoListFragment>(R.id.fragment_container_view)
            }
        }

        binding.fab.setOnClickListener {
            AddToDoDialogFragment().show(supportFragmentManager, "AddToDoDialogFragment")
        }
    }
}
