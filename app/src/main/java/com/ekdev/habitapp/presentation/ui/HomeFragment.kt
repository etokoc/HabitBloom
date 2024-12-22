package com.ekdev.habitapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekdev.habitapp.R
import com.ekdev.habitapp.databinding.FragmentHomeBinding
import com.ekdev.habitapp.domain.model.CardItem
import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.presentation.adapter.HomeListAdapter
import com.ekdev.habitapp.util.setGradientColor


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initData()
        return binding.root
    }

    private fun initData() {
        binding.apply {
            val adapter = HomeListAdapter().apply {
                submitList(getCards())
            }
            recyclerView.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                isNestedScrollingEnabled = false
            }
        }
    }

    private fun getCards(): List<CardItem<Habit>> {
        val list = ArrayList<CardItem<Habit>>()
        val todayHabitList = ArrayList<Habit>()
        val yourGoals = ArrayList<Habit>()
        todayHabitList.add(Habit(1, "Araba sür", "", false))
        todayHabitList.add(Habit(2, "Okula git", "", false))
        todayHabitList.add(Habit(3, "Okula git", "", false))
        todayHabitList.add(Habit(1, "Okula git", "", false))
        todayHabitList.add(Habit(1, "Okula git", "", false))
        todayHabitList.add(Habit(1, "Okula git", "", false))
        todayHabitList.add(Habit(1, "Okula git", "", false))


        yourGoals.add(Habit(3, "Okula git", "", false))
        yourGoals.add(Habit(1, "Okula git", "", false))
        yourGoals.add(Habit(1, "Okula git", "", false))
        yourGoals.add(Habit(1, "Okula git", "", false))
        yourGoals.add(Habit(1, "Okula git", "", false))

        val todayHabits = CardItem<Habit>("Today Habit", todayHabitList)
        val yourGoalss = CardItem<Habit>("Your Goals", yourGoals)
        list.add(todayHabits)
        list.add(yourGoalss)
        return list
    }
}