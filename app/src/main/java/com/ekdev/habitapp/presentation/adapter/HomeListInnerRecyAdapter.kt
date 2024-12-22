package com.ekdev.habitapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekdev.habitapp.databinding.HomeCheckboxStyleItemBinding
import com.ekdev.habitapp.domain.model.Habit

class HomeListInnerAdapter :
    ListAdapter<Habit, HomeListInnerAdapter.ViewHolder>(ListItemDiffCallback()) {

    private val MAX_ROW_COUNT: Int = 3
    private var isExpanded = false
    private var displayedList: List<Habit> = emptyList()

    init {
        updateDisplayedList()
    }

    inner class ViewHolder(private val binding: HomeCheckboxStyleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(habit: Habit) {
            binding.apply {
                tvTitle.text = habit.name
                rootContainer.apply {
                    isSelected = checkbox.root.isChecked
                    checkbox.root.setOnCheckedChangeListener { _, isChecked ->
                        isSelected = isChecked
                    }
                    rootView.setOnClickListener {
                        checkbox.root.isChecked = !isSelected
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HomeCheckboxStyleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(displayedList[position])
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

    class ListItemDiffCallback : DiffUtil.ItemCallback<Habit>() {
        override fun areItemsTheSame(oldItem: Habit, newItem: Habit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Habit, newItem: Habit): Boolean {
            return oldItem == newItem
        }
    }
}
