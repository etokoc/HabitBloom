package com.ekdev.habitapp.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ekdev.habitapp.R
import com.ekdev.habitapp.databinding.FragmentAddHabitBinding
import com.ekdev.habitapp.presentation.viewmodel.HabitViewModel

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

        dialog?.window?.let {window->
            val windowManager = window.attributes
            windowManager.width = ViewGroup.LayoutParams.MATCH_PARENT
            windowManager.height = ViewGroup.LayoutParams.WRAP_CONTENT
            window.attributes = windowManager
        }
    }
}