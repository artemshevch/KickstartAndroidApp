package com.wix.kickstartapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wix.kickstartapp.model.Task

class TaskListViewModel : ViewModel() {
    private val stateLiveData = MutableLiveData<State>()

    private val items = mutableListOf(
        Task(title = "Buy a bread", finished = false),
        Task(title = "Buy milk", finished = true)
    )

    init {
        stateLiveData.value = State.TasksList(items)
    }

    fun getStateLiveData(): LiveData<State> = stateLiveData

    fun addTask(title: String) {
        items.add(0, Task(title = title, finished = false))
        stateLiveData.value = State.TasksList(items)
    }

    sealed class State {
        object EmptyList : State()
        data class TasksList(
            val items: List<Task>
        ) : State()
    }
}