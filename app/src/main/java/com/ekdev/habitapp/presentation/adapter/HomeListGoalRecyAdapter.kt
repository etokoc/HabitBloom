package com.ekdev.habitapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.*
import com.ekdev.habitapp.R
import com.ekdev.habitapp.databinding.HomeCheckboxStyleItemBinding
import com.ekdev.habitapp.databinding.HomeProgressStyleItemBinding
import com.ekdev.habitapp.domain.model.EnumCardType
import com.ekdev.habitapp.domain.model.Goal
import com.ekdev.habitapp.domain.model.Habit

class HomeListGoalAdapter(
    private var onItemClicked: ((Goal) -> Unit)? = null
) :
    ListAdapter<Goal, HomeListGoalAdapter.TodayGoalsViewHolder>(ListItemDiffCallback()) {

    companion object {
        const val MAX_ROW_COUNT: Int = 3
    }

    private var isExpanded = false
    private var displayedList: List<Goal> = emptyList()

    init {
        updateDisplayedList()
    }

    inner class TodayGoalsViewHolder(private val binding: HomeProgressStyleItemBinding) :
        ViewHolder(binding.root) {
        fun bind(goal: Goal) {
            binding.apply {
                tvTitle.text = goal.name
                btnDetail.root.setOnClickListener {
                    showDetailPopup(it, goal)
                }
            }
        }
    }


    private fun showDetailPopup(root: View, goal: Goal) {
        val popupMenu = PopupMenu(root.context, root)
        popupMenu.inflate(R.menu.detail_menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.edit -> {
                    true
                }

                R.id.delete -> {
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }

    private fun clickItem(goal: Goal, checked: Boolean) {
        val clickedGoal =
            Goal(goal.id, goal.name, checked, goal.startDate, goal.endDate)
        onItemClicked?.invoke(clickedGoal)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeListGoalAdapter.TodayGoalsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeProgressStyleItemBinding.inflate(
            inflater,
            parent,
            false
        )
        return TodayGoalsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodayGoalsViewHolder, position: Int) {
        val goal = displayedList[position]
        holder.bind(goal)
    }

    override fun getItemCount(): Int = displayedList.size

    override fun submitList(list: List<Goal>?) {
        super.submitList(list)
        updateDisplayedList()
    }

    fun setExpanded(expanded: Boolean) {
        isExpanded = expanded
        updateDisplayedList()
    }

    private fun updateDisplayedList() {
        displayedList = if (isExpanded) currentList else currentList.take(MAX_ROW_COUNT)
        notifyDataSetChanged()
    }

    class ListItemDiffCallback : DiffUtil.ItemCallback<Goal>() {
        override fun areItemsTheSame(oldItem: Goal, newItem: Goal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Goal, newItem: Goal): Boolean {
            return oldItem == newItem
        }
    }
}
