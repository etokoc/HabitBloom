package com.ekdev.habitapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.*
import com.ekdev.habitapp.databinding.HomeCheckboxStyleItemBinding
import com.ekdev.habitapp.databinding.HomeProgressStyleItemBinding
import com.ekdev.habitapp.domain.model.EnumCardType
import com.ekdev.habitapp.domain.model.Habit

class HomeListInnerAdapter(private var onItemClicked: ((Habit) -> Unit)? = null
) :
    ListAdapter<Habit, ViewHolder>(ListItemDiffCallback()) {

    companion object {
        const val MAX_ROW_COUNT: Int = 3
    }

    private lateinit var _cardType: EnumCardType
    private var isExpanded = false
    private var displayedList: List<Habit> = emptyList()

    init {
        updateDisplayedList()
    }

    inner class TodayHabitViewHolder(private val binding: HomeCheckboxStyleItemBinding) :
        ViewHolder(binding.root) {
        fun bind(habit: Habit) {
            binding.apply {
                tvTitle.text = habit.name
                rootContainer.apply {
                    isSelected = habit.isCompleted
                    checkbox.root.isChecked = habit.isCompleted
                    checkbox.root.setOnCheckedChangeListener { _, isChecked ->
                        isSelected = isChecked
                        clickItem(habit, isChecked)
                    }
                    rootView.setOnClickListener {
                        checkbox.root.isChecked = !isSelected
                        clickItem(habit, isSelected)
                    }
                }
            }
        }
    }

    inner class TodayGoalsViewHolder(private val binding: HomeProgressStyleItemBinding) :
        ViewHolder(binding.root) {
        fun bind(habit: Habit) {
            binding.apply {
                tvTitle.text = habit.description
                rootContainer.apply {

                }
            }
        }
    }

    private fun clickItem(habit: Habit, checked: Boolean) {
        val clickedHabit = Habit(habit.id, habit.name, habit.description, checked)
        onItemClicked?.invoke(clickedHabit)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (_cardType) {
            EnumCardType.YOUR_GOALS_CARD -> {
                val binding = HomeProgressStyleItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )
                return TodayGoalsViewHolder(binding)
            }

            EnumCardType.TODAY_HABIT_CARD -> {
                val binding = HomeCheckboxStyleItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )
                return TodayHabitViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid card type")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val habit = displayedList[position]
        when (holder) {
            is TodayGoalsViewHolder -> holder.bind(habit)
            is TodayHabitViewHolder -> holder.bind(habit)
        }
    }

    override fun getItemCount(): Int = displayedList.size

    override fun submitList(list: List<Habit>?) {
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

    fun setType(cardType: EnumCardType) {
        _cardType = cardType
    }

    class ListItemDiffCallback : DiffUtil.ItemCallback<Habit>() {
        override fun areItemsTheSame(oldItem: Habit, newItem: Habit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Habit, newItem: Habit): Boolean {
            return oldItem == newItem
        }
    }
}
