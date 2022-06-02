package com.example.shareTask.presentation.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.utilities.converterTaskModelToJson
import com.example.domain.models.TaskModel
import com.example.shareTask.R
import com.example.shareTask.app.ShareTask
import com.example.shareTask.databinding.FragmentTasksBinding
import com.example.shareTask.presentation.tasks.dialogs.CreateNewTaskDialogFragment
import com.example.shareTask.presentation.tasks.dialogs.GetFriendTaskDialogFragment
import com.example.shareTask.presentation.tasks.dialogs.ShareWithFriendDialogFragment
import javax.inject.Inject


class TasksFragment : Fragment(), TaskActionListener {

    private var _binding: FragmentTasksBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel : TasksViewModel

    @Inject
    lateinit var tasksViewModelFactory: TasksViewModelFactory

    @Inject
    lateinit var adapter: TasksAdapter

    private var isOpen = false;

    private lateinit var fabOpen: Animation

    private lateinit var fabClose: Animation

    private lateinit var rotateForward: Animation

    private lateinit var rotateBackward: Animation


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity?.applicationContext as ShareTask).appComponent.inject(this)
        viewModel = ViewModelProvider(this, tasksViewModelFactory)[TasksViewModel::class.java]
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        adapter.taskActionListener = this

        fabOpen = AnimationUtils.loadAnimation(context, R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(context, R.anim.fab_close)
        rotateForward = AnimationUtils.loadAnimation(context, R.anim.rotate_forward)
        rotateBackward = AnimationUtils.loadAnimation(context, R.anim.rotate_backward)


        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        setObserver()
        setListener()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uploadTasks()
    }
    private fun setListener(){
        binding.mainFAB.setOnClickListener{
            animateFab()
        }
        binding.createNewTask.setOnClickListener{
            val dialogFragment = CreateNewTaskDialogFragment()
            dialogFragment.show(childFragmentManager,"Dialog")
        }

        binding.getFriendTask.setOnClickListener{
            val dialogFragment = GetFriendTaskDialogFragment()
            dialogFragment.show(childFragmentManager,"Dialog")
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && binding.mainFAB.visibility == View.VISIBLE) {
                    if (isOpen) {
                        binding.getFriendTask.hide()
                        binding.createNewTask.hide()
                    }
                    binding.mainFAB.hide()
                }
                else if(dy < 0 && binding.mainFAB.visibility != View.VISIBLE) {
                    if (isOpen) {
                        binding.getFriendTask.show()
                        binding.createNewTask.show()
                    }
                    binding.mainFAB.show()
                }
            }
        })
    }

    private fun setObserver() {
        viewModel.taskList.observe(viewLifecycleOwner){
            if ( it != null){
                adapter.tasks = it.toMutableList()
                println(it)
            }
        }
    }

    private fun animateFab() {
        if (isOpen) {
            binding.mainFAB.startAnimation(rotateForward)
            binding.getFriendTask.startAnimation(fabClose)
            binding.createNewTask.startAnimation(fabClose)
            binding.getFriendTask.isClickable = false
            binding.createNewTask.isClickable = false
            isOpen = false
        } else {
            binding.mainFAB.startAnimation(rotateBackward)
            binding.getFriendTask.startAnimation(fabOpen)
            binding.createNewTask.startAnimation(fabOpen)
            binding.getFriendTask.isClickable = true
            binding.createNewTask.isClickable = true
            isOpen = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModelStore.clear()
    }

    override fun onOpenTask(task: TaskModel, view: View) {
        val bundle = Bundle()
        bundle.putString("ARG_TASK", converterTaskModelToJson(task))
        Navigation.findNavController(view).navigate(R.id.taskWindowFragment, bundle)
    }

    override fun onShareTask(task: TaskModel) {
        val dialog = ShareWithFriendDialogFragment(task.id)
        dialog.show(childFragmentManager,"Dialog")
    }

    override fun onDeleteTask(task: TaskModel) {
        viewModel.deleteTask(task)
    }


}