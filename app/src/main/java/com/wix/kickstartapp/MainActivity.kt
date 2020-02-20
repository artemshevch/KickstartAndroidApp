package com.wix.kickstartapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_tasks_list.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TaskListViewModel
    private val adapter = TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = adapter

        btAddTask.isEnabled = false
        etTask.addTextChangedListener { text ->
            btAddTask.isEnabled = text?.length != 0
        }
        btAddTask.setOnClickListener {
            viewModel.addTask(etTask.text.toString())
            etTask.setText("")
        }

        viewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(TaskListViewModel::class.java)
        viewModel.getStateLiveData().observe(this, Observer { state ->
            renderState(state)
        })

        tvShareApp.setOnClickListener {
            shareApp()
        }
    }

    private fun renderState(state: TaskListViewModel.State) {
        when (state) {
            TaskListViewModel.State.EmptyList -> {
                vwEmptyList.visibility = View.VISIBLE
                vwContentList.visibility = View.GONE
            }
            is TaskListViewModel.State.TasksList -> {
                vwEmptyList.visibility = View.GONE
                vwContentList.visibility = View.VISIBLE
                adapter.updateItems(state.items)
            }
        }
    }

    private fun shareApp() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Wow, this is brilliant app!")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}