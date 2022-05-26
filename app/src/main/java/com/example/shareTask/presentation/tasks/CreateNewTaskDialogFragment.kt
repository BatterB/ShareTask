package com.example.shareTask.presentation.tasks

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.shareTask.R
import com.example.shareTask.app.ShareTask
import com.example.shareTask.databinding.DialogFragmentCreateNewTaskBinding
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CreateNewTaskDialogFragment : DialogFragment(){

    private var _binding: DialogFragmentCreateNewTaskBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel : TasksViewModel

    @Inject
    lateinit var tasksViewModelFactory: TasksViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity?.applicationContext as ShareTask).appComponent.inject(this)

        _binding = DialogFragmentCreateNewTaskBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this,tasksViewModelFactory)[TasksViewModel::class.java]

        setEventListener()

        return binding.root
    }

    private fun setEventListener(){

        binding.buttonDatePicker.setOnClickListener{

            val calendar = Calendar.getInstance()
            val thisYear = calendar.get(Calendar.YEAR)
            val thisMonth = calendar.get(Calendar.MONTH)
            val thisDay = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(requireContext(),
                { _, year, monthOfYear, dayOfMonth ->
                    binding.dateText.text = "$dayOfMonth.$monthOfYear.$year" },
                thisYear, thisMonth, thisDay)

            datePickerDialog.show()
        }

        binding.buttonCreate.setOnClickListener{
            val title = binding.titleNewTask.text.toString()
            val priority = binding.spinnerPriority.selectedItem.toString()
            val textDate = binding.dateText.text.toString()
            val date : Date = SimpleDateFormat("dd.MM.yyyy",Locale.US).parse(textDate) as Date
            viewModel.createTask(title, priority, date)
            dismiss()
        }

        binding.buttonCancel.setOnClickListener{
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}