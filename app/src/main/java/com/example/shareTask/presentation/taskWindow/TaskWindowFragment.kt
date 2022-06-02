package com.example.shareTask.presentation.taskWindow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.utilities.converterJsonToTaskModel
import com.example.shareTask.app.ShareTask
import com.example.shareTask.databinding.FragmentTaskWindowBinding
import javax.inject.Inject

class TaskWindowFragment : Fragment() {

    private var _binding : FragmentTaskWindowBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel : TaskWindowViewModel

    @Inject
    lateinit var taskWindowViewModelFactory: TaskWindowViewModelFactory

    @Inject
    lateinit var adapter: TaskWindowAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTaskWindowBinding.inflate(inflater,container,false)

        (activity?.applicationContext as ShareTask).appComponent.inject(this)

        viewModel = ViewModelProvider(this,taskWindowViewModelFactory)[TaskWindowViewModel::class.java]
        savedInstanceState?.get("ARG_TASK")
        val task = converterJsonToTaskModel(arguments?.getString("ARG_TASK")!!)
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        binding.taskTitle.text = task.title
        adapter.taskPoints = task.taskPoints.toMutableList()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModelStore.clear()
    }
}