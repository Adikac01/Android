package com.example.zadanie4

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import com.example.zadanie4.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class NewTaskSheet(var taskItem: TaskItem?, context: Context) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (taskItem != null) {
        } else {
            binding.taskTitle.text = "New task"
        }
        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveAction() {
        val name = binding.name.text.toString()
        val desc = binding.desc.text.toString()
        if (taskItem == null) {
            val newTask = TaskItem(name, desc)
            taskViewModel.addTaskItem(newTask)
            val service = NotificationService(requireContext())
            createNotificationChannel(newTask.name)
            service.showNotification(newTask.name)
        }
        binding.name.setText("")
        binding.desc.setText("")
        dismiss()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(name: String) {
        val desc = "Description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("1", name, importance)
        channel.description = desc
        val notificationManager = this.context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}