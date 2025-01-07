package com.ekdev.habitapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekdev.habitapp.R
import com.ekdev.habitapp.databinding.FragmentHomeBinding
import com.ekdev.habitapp.domain.model.CardItem
import com.ekdev.habitapp.presentation.adapter.HomeListAdapter
import com.ekdev.habitapp.presentation.ui.base.BaseFragment
import com.ekdev.habitapp.presentation.viewmodel.GoalViewModel
import com.ekdev.habitapp.presentation.viewmodel.HabitLogViewModel
import com.ekdev.habitapp.presentation.viewmodel.HabitViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val habitViewModel by activityViewModels<HabitViewModel>()
    private val habitLogViewModel by activityViewModels<HabitLogViewModel>()
    private val goalViewModel by activityViewModels<GoalViewModel>()
    private lateinit var adapter: HomeListAdapter
    private lateinit var cardList: ArrayList<CardItem<*>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        cardList = arrayListOf()
        initData()
        initUI()
    }

    private fun initData() {
        binding.apply {
            adapter = HomeListAdapter({ habit, isCompleted ->
                habitLogViewModel.addOrUpdateHabitLog(habitId = habit.id!!, isCompleted)
                refreshData()
            });
            recyclerView.apply {
                adapter = this@HomeFragment.adapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                isNestedScrollingEnabled = false
            }
        }
    }

    private fun refreshData() {
        habitViewModel.getHabitCounts()
        habitViewModel.loadCards()
    }

    private fun initUI() {
        binding.apply {
            btnAddFab.setOnClickListener {
                showAddHabitDialog()
            }
            tvDate.text = getTodayDateString()
        }

        habitViewModel.cardItems.observe(this) {
            cardList.clear()
            cardList.addAll(it)
            adapter.submitList(cardList)
        }

        habitViewModel.habitCount.observe(this) {
            binding.tvHabitCount.text =
                getString(R.string.habit_counts, it.completedHabitCount, it.totalCount)
            val progress = if (it.totalCount != null && it.totalCount!! > 0) {
                (it.completedHabitCount!!.toInt() * 100) / it.totalCount!!.toInt()
            } else {
                0
            }
            binding.customMainProgressBar.progressBar.progress = progress
            binding.customMainProgressBar.tvProgressBar.text =
                getString(R.string.progress_tv, progress)
        }
        habitViewModel.getHabitCounts()
        habitViewModel.loadCards()
    }

    private fun getTodayDateString(): CharSequence? {
        val date = Date()
        val format = SimpleDateFormat("EEE d MMMM yyyy", Locale.getDefault())
        val formattedDate = format.format(date)
        return formattedDate
    }

    private fun showAddHabitDialog() {
        val dialogFragment = AddHabitFragment()
        dialogFragment.show(requireActivity().supportFragmentManager, "AddHabitFragment")
    }
}