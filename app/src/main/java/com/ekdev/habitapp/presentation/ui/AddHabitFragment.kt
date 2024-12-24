package com.ekdev.habitapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ekdev.habitapp.R
import com.ekdev.habitapp.databinding.FragmentAddHabitBinding
import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.presentation.viewmodel.HabitViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddHabitFragment : DialogFragment() {
    private val viewModel: HabitViewModel by viewModels()
    private lateinit var binding: FragmentAddHabitBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddHabitBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        initUI()
    }

    private fun initUI() {
        dialog?.window?.let { window ->
            val windowManager = window.attributes
            windowManager.width = ViewGroup.LayoutParams.MATCH_PARENT
            windowManager.height = ViewGroup.LayoutParams.WRAP_CONTENT
            window.attributes = windowManager
            window.setBackgroundDrawableResource(android.R.color.transparent)
        }
        binding.apply {
            btnCloseDialog.setOnClickListener {
                dismiss()
            }
            btnAddHabit.setOnClickListener {
                addHabit()
            }
        }
    }

    private fun addHabit() {
        val habitName = binding.editTextHabitName.text.toString()
        val habitGoal = binding.editTextHabitGoal.text.toString()
        viewModel.addHabit(Habit(name = habitName, description = habitGoal, isCompleted = false))
        dismiss()
        findNavController().navigate(R.id.successAddHabitFragment)
    }

}