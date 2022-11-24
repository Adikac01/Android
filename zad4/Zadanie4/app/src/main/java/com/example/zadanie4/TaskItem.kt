package com.example.zadanie4

import java.util.UUID

class TaskItem(
    var name: String,
    var desc: String,
    var id: UUID = UUID.randomUUID(),
    var completed: Boolean = false
)
{
    fun isCompleted() = completed

    fun checkbox(): Int = if(isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24


}