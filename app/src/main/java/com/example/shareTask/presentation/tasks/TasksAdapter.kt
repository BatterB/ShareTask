package com.example.shareTask.presentation.tasks

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.TaskModel
import com.example.shareTask.R
import com.example.shareTask.databinding.TasksItemBinding
import javax.inject.Inject


class TasksAdapter @Inject constructor(
    private val taskActionListener: TaskActionListener
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
                showPopupMenu(view)
            }
            else ->{
                taskActionListener.onOpenTask(task,view)
            }
        }
    }

    private fun showPopupMenu(view: View){
        val context = view.context
        val popupMenu = PopupMenu(context, view)
        val task = view.tag as TaskModel
        popupMenu.menu.add(0,1, Menu.NONE,context.getString(R.string.delete)).apply {  }
        popupMenu.menu.add(0,2,Menu.NONE,context.getString(R.string.share)).apply {  }

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                1 -> println(1)
                2 -> println(2)
            }
            return@setOnMenuItemClickListener true
        }

        popupMenu.show()
    }
}