package com.example.shareTask.presentation.taskWindow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.shareTask.R
import com.example.shareTask.databinding.TaskPointBinding


class TaskWindowAdapter : RecyclerView.Adapter<TaskWindowAdapter.TaskWindowViewHolder>() {

    class TaskWindowViewHolder(
        val binding: TaskPointBinding
    ) : RecyclerView.ViewHolder(binding.root)

    var taskPoints : MutableList<List<String>> = mutableListOf()
        set (newValue){
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): TaskWindowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskPointBinding.inflate(inflater, parent,false)
        return TaskWindowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskWindowViewHolder, position: Int) {
        val taskPoint = taskPoints[position]
        with(holder.binding) {
            holder.itemView.tag = taskPoint
            checkBox.tag = taskPoint
            pointName.text = taskPoint[0]
            if (taskPoint[1] == "true") {
                checkBox.isChecked = true
                holder.itemView.background =
                    ContextCompat.getDrawable(root.context, R.color.md_theme_light_inversePrimary)
            }
        }

    }

    override fun getItemCount(): Int = taskPoints.size
}