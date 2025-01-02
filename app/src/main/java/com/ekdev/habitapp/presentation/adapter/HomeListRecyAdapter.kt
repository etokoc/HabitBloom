package com.ekdev.habitapp.presentation.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ekdev.habitapp.R
import com.ekdev.habitapp.databinding.HomeListItemBinding
import com.ekdev.habitapp.domain.model.CardItem
import com.ekdev.habitapp.domain.model.EnumCardType
import com.ekdev.habitapp.domain.model.Goal
import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.domain.model.HabitWithLogs
import com.ekdev.habitapp.util.setGradientColor

class HomeListAdapter(
    private var onItemClickedForHabit: ((Habit) -> Unit)? = null,
    private var onItemClickedForGoal: ((Goal) -> Unit)? = null
) :
    ListAdapter<CardItem<*>, HomeListAdapter.HomeListViewHolder>(ListItemDiffCallback()) {

    private var isExpanded = false


    inner class HomeListViewHolder(private val binding: HomeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val habitAdapter = HomeListHabitAdapter(onItemClicked = onItemClickedForHabit)
        private val goalAdapter = HomeListGoalAdapter(onItemClicked = onItemClickedForGoal)

        init {
            binding.recyclerView.apply {
                isNestedScrollingEnabled = false
                layoutManager = LinearLayoutManager(
                    binding.root.context, LinearLayoutManager.VERTICAL, false
                )
            }

            binding.seeAllButton.setOnClickListener {
                isExpanded = !isExpanded
                if (getItem(0).cardType == EnumCardType.TODAY_HABIT_CARD) {
                    habitAdapter.setExpanded(isExpanded)
                } else {
                    goalAdapter.setExpanded(isExpanded)
                }
                if (isExpanded) {
                    binding.seeAllButton.text = binding.root.context.getString(R.string.see_less)
                } else {
                    binding.seeAllButton.text = binding.root.context.getString(R.string.see_all)
                }

            }
        }

        fun bind(cardItem: CardItem<*>) {
            binding.apply {
                tvTitle.text = cardItem.title
                seeAllButton.setGradientColor(
                    intArrayOf(
                        itemView.context.getColor(R.color.orange_start_color),
                        itemView.context.getColor(R.color.orange_end_color)
                    )
                )
                recyclerView.addItemDecoration(SpacingItemDecoration(bottom = 66))

                if (cardItem.cardType == EnumCardType.TODAY_HABIT_CARD) {
                    binding.recyclerView.adapter = habitAdapter
                    habitAdapter.submitList(cardItem.dataList as List<HabitWithLogs>)
                } else if (cardItem.cardType == EnumCardType.YOUR_GOALS_CARD) {
                    binding.recyclerView.adapter = goalAdapter
                    goalAdapter.submitList(cardItem.dataList as List<Goal>)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListViewHolder {
        val binding = HomeListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HomeListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ListItemDiffCallback : DiffUtil.ItemCallback<CardItem<*>>() {
        override fun areItemsTheSame(oldItem: CardItem<*>, newItem: CardItem<*>): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: CardItem<*>, newItem: CardItem<*>
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].cardType.value
    }
}

//Spacing for margin apply to recyclerview
class SpacingItemDecoration(
    private val top: Int? = null,
    private val bottom: Int? = null,
    private val left: Int? = null,
    private val right: Int? = null
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        bottom?.let {
            outRect.bottom = it
        }
        top?.let {
            outRect.top = it
        }
        left?.let {
            outRect.left = it
        }
        right?.let {
            outRect.right = it
        }
    }

}
