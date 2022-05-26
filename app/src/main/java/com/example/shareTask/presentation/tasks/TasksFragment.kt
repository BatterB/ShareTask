package com.example.shareTask.presentation.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shareTask.R
import com.example.shareTask.app.ShareTask
import com.example.shareTask.databinding.FragmentTasksBinding
import javax.inject.Inject

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel : TasksViewModel

    private lateinit var dialogFragment: CreateNewTaskDialogFragment

    @Inject
    lateinit var tasksViewModelFactory: TasksViewModelFactory

    @Inject
    lateinit var adapter: TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity?.applicationContext as ShareTask).appComponent.inject(this)
        viewModel = ViewModelProvider(this,tasksViewModelFactory)[TasksViewModel::class.java]
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        dialogFragment = CreateNewTaskDialogFragment()


        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        setObserver()
        setListener()
        viewModel.uploadTasks()

        return binding.root
    }
    private fun setListener(){
        binding.mainFAB.setOnClickListener{
            dialogFragment.show(childFragmentManager,"Dialog")
        }
    }
    private fun setObserver() {
        viewModel.taskList.observe(viewLifecycleOwner){
            if ( it != null){
                adapter.tasks = it.toMutableList()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}