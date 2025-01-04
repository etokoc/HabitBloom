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
import com.ekdev.habitapp.domain.model.EnumCardType
import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.domain.model.HabitWithLogs
import com.ekdev.habitapp.presentation.adapter.HomeListHabitAdapter.TodayHabitViewHolder

class HomeListHabitAdapter(
    private var onItemClicked: ((Habit, Boolean) -> Unit)? = null
) :
    ListAdapter<HabitWithLogs, TodayHabitViewHolder>(ListItemDiffCallback()) {

    companion object {
        const val MAX_ROW_COUNT: Int = 3
    }

    private lateinit var _cardType: EnumCardType
    private var isExpanded = false
    private var displayedList: List<HabitWithLogs> = emptyList()

    init {
        updateDisplayedList()
    }

    inner class TodayHabitViewHolder(private val binding: HomeCheckboxStyleItemBinding) :
        ViewHolder(binding.root) {
        fun bind(habit: HabitWithLogs) {
            binding.apply {
                tvTitle.text = habit.habit.title
                rootContainer.apply {
                    // TODO: Habit withlog ile gelecek
                    isSelected = habit.isTodayCompleted ?: false
                    checkbox.root.isChecked = isSelected
                    checkbox.root.setOnCheckedChangeListener { _, isChecked ->
                        isSelected = isChecked
                        clickItem(habit.habit, isChecked)
                    }
                    rootView.setOnClickListener {
                        checkbox.root.isChecked = !isSelected
                        clickItem(habit.habit, isSelected)
                    }
                    btnDetail.root.setOnClickListener {
                        showDetailPopup(it, habit.habit)
                    }
                }
            }
        }
    }

    private fun showDetailPopup(root: View, habit: Habit) {
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

    private fun clickItem(habit: Habit, checked: Boolean) {
        onItemClicked?.invoke(habit, checked)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodayHabitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeCheckboxStyleItemBinding.inflate(
            inflater,
            parent,
            false
        )
        return TodayHabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodayHabitViewHolder, position: Int) {
        val habit = displayedList[position]
        holder.bind(habit)
    }

    override fun getItemCount(): Int = displayedList.size

    override fun submitList(list: List<HabitWithLogs>?) {
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

    class ListItemDiffCallback : DiffUtil.ItemCallback<HabitWithLogs>() {
        override fun areItemsTheSame(oldItem: HabitWithLogs, newItem: HabitWithLogs): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HabitWithLogs, newItem: HabitWithLogs): Boolean {
            return oldItem == newItem
        }
    }
}
