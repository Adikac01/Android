package com.example.zadanie4

interface TaskItemClickListener {
    fun completeTaskItem(taskItem: TaskItem)
    fun deleteTaskItem(taskItem: TaskItem)
}