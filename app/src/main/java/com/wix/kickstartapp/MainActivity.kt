package com.wix.kickstartapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.wix.kickstartapp.model.Task
import kotlinx.android.synthetic.main.layout_tasks_list.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = listOf(
            Task(title = "Buy a bread", finished = false),
            Task(title = "Buy milk", finished = true)
        )

        val adapter = TaskAdapter()
        adapter.updateItems(items)

        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = adapter
    }
}