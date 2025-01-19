package com.ekdev.habitapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.ekdev.habitapp.R
import com.ekdev.habitapp.databinding.FragmentProgressBinding
import com.ekdev.habitapp.presentation.viewmodel.GoalViewModel
import com.ekdev.habitapp.presentation.viewmodel.HabitViewModel
import kotlinx.coroutines.launch

class ProgressFragment : Fragment() {
    private lateinit var binding: FragmentProgressBinding
    private val habitViewModel by activityViewModels<HabitViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        fillData()
        observeData()
        lifecycleScope.launch {
            habitViewModel.getHabitCounts()
        }
    }

    private fun observeData() {
        habitViewModel.habitCount.observe(viewLifecycleOwner) {
            binding.tvHabitCount.text =
                getString(R.string.habit_counts, it.completedHabitCount, it.totalCount)
            val progress = if (it.totalCount != null && it.totalCount!! > 0) {
                (it.completedHabitCount!!.toInt() * 100) / it.totalCount!!.toInt()
            } else {
                0
            }
            binding.customMainProgressBar.tvProgressBar.textSize = 24f
            binding.customMainProgressBar.progressBar.progress = progress
            binding.customMainProgressBar.tvProgressBar.text =
                getString(R.string.progress_tv, progress)
        }
    }


    private fun fillData() {
        binding.apply {
        }
    }

}