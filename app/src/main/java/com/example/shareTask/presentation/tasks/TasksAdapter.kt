package com.example.shareTask.presentation.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.TaskModel
import com.example.shareTask.R
import com.example.shareTask.databinding.TasksItemBinding
import javax.inject.Inject



class TasksAdapter @Inject constructor(taskActionListener: TaskActionListener
): RecyclerView.Adapter<TasksAdapter.TaskViewHolder>(), View.OnClickListener {

    var tasks : MutableList<TaskModel> = mutableListOf()
        set (newValue){
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TasksItemBinding.inflate(inflater, parent,false)
        binding.root.setOnClickListener(this)
        binding.moreButton.setOnClickListener(this)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        with(holder.binding){
            holder.itemView.tag = task
            moreButton.tag = task
            taskName.text = task.title
        }
    }

    override fun getItemCount(): Int = tasks.size

    class TaskViewHolder(
        val binding : TasksItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(view: View) {
        val task = view.tag as TaskModel

        when (view.id){
            R.id.more_button ->{
                Toast.makeText(view.context,"More button", Toast.LENGTH_SHORT).show()
            }
            else ->{
                Toast.makeText(view.context,"${task.title}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}