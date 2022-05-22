package com.example.shareTask.presentation.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.TaskModel
import com.example.shareTask.databinding.TasksItemBinding

class TasksAdapter: RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    var tasks : MutableList<TaskModel> = mutableListOf()
        set (newValue){
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TasksItemBinding.inflate(inflater, parent,false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        with(holder.binding){
            taskName.text = task.title
        }
    }

    override fun getItemCount(): Int = tasks.size

    class TaskViewHolder(
        val binding : TasksItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}