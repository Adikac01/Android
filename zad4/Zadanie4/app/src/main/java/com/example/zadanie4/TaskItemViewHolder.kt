package com.example.zadanie4

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie4.databinding.TaskItemCellBinding

class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListener
    ) :RecyclerView.ViewHolder(binding.root) {

        fun bindTaskItem(taskItem:TaskItem){
            binding.name.text = taskItem.name
            if (taskItem.isCompleted()){
                binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }

            binding.doneButton.setImageResource(taskItem.checkbox())

            binding.doneButton.setOnClickListener{
                clickListener.completeTaskItem(taskItem)
            }
        }
}