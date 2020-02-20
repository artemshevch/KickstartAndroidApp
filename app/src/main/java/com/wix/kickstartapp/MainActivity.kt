package com.wix.kickstartapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.wix.kickstartapp.model.Task
import kotlinx.android.synthetic.main.layout_tasks_list.*

class MainActivity : AppCompatActivity() {
    private val items = mutableListOf(
        Task(title = "Buy a bread", finished = false),
        Task(title = "Buy milk", finished = true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = TaskAdapter()
        adapter.updateItems(items)

        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = adapter

        btAddTask.isEnabled = false
        etTask.addTextChangedListener { text ->
            btAddTask.isEnabled = text?.length != 0
        }
        btAddTask.setOnClickListener {
            createNewTask(adapter)
            etTask.setText("")
        }
    }

    private fun createNewTask(adapter: TaskAdapter) {
        val task = Task(etTask.text.toString(), finished = false)
        items.add(0, task)
        adapter.updateItems(items)
    }
}