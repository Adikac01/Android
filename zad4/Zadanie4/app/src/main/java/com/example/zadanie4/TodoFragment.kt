package com.example.zadanie4

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zadanie4.databinding.FragmentTodoBinding

class TodoFragment : Fragment(), TaskItemClickListener{

    private lateinit var binding: FragmentTodoBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTodoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)
        binding.newTaskBtn.setOnClickListener{
            NewTaskSheet(null).show(parentFragmentManager,"newTaskTag")
        }
        setRecycleView()
    }

    private fun setRecycleView(){
        val mainActivity = this
        taskViewModel.taskItems.observe(viewLifecycleOwner){
            binding.todoListRecyclerView.apply{
                layoutManager = LinearLayoutManager(requireContext())
                adapter = it?.let { it1 -> TaskItemAdapter(it1,mainActivity) }
            }
        }
    }

    override fun completeTaskItem(taskItem: TaskItem) {
        taskViewModel.setCompleted(taskItem)
    }

    override fun deleteTaskItem(taskItem: TaskItem) {
        taskViewModel.removeTaskItem(taskItem)
    }
}