package com.ekdev.habitapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekdev.habitapp.R
import com.ekdev.habitapp.databinding.FragmentHomeBinding
import com.ekdev.habitapp.domain.model.CardItem
import com.ekdev.habitapp.domain.model.EnumCardType
import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.presentation.adapter.HomeListAdapter
import com.ekdev.habitapp.presentation.ui.base.BaseFragment
import com.ekdev.habitapp.presentation.viewmodel.HabitViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by activityViewModels<HabitViewModel>()
    private lateinit var adapter: HomeListAdapter

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
        initData()
        initUI()
    }

    private fun initData() {
        binding.apply {
            adapter = HomeListAdapter(onItemClicked = { updatedHabit ->
                viewModel.updateHabit(updatedHabit)
            });
            recyclerView.apply {
                adapter = this@HomeFragment.adapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                isNestedScrollingEnabled = false
            }
        }
    }

    private fun initUI() {
        binding.apply {
            btnAddFab.setOnClickListener {
                showAddHabitDialog()
            }
        }
        viewModel.habits.observe(viewLifecycleOwner) { habits ->
            fillCards(habits)
        }
        viewModel.getHabits()
    }

    private fun fillCards(habits: List<Habit>? = null) {
        val todayHabits = CardItem(getString(R.string.today_habit), habits ?: emptyList(),EnumCardType.TODAY_HABIT_CARD)
        val yourGoals = CardItem(getString(R.string.your_goals), habits ?: emptyList(),EnumCardType.YOUR_GOALS_CARD)
        val mainCardList = listOf(todayHabits, yourGoals)
        adapter.submitList(mainCardList)
    }


    private fun showAddHabitDialog() {
        val dialogFragment = AddHabitFragment()
        dialogFragment.show(requireActivity().supportFragmentManager, "AddHabitFragment")
    }
}