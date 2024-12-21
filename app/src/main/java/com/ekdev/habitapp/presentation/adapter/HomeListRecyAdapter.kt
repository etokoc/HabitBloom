package com.ekdev.habitapp.presentation.adapter

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
import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.util.setGradientColor

class HomeListAdapter :
    ListAdapter<CardItem<Habit>, HomeListAdapter.HomeListViewHolder>(ListItemDiffCallback()) {

    inner class HomeListViewHolder(private val binding: HomeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val innerAdapter = HomeListInnerAdapter()

        init {
            binding.recyclerView.apply {
                isNestedScrollingEnabled = false
                layoutManager = LinearLayoutManager(
                    binding.root.context, LinearLayoutManager.VERTICAL, false
                )
                adapter = innerAdapter
            }
        }

        fun bind(cardItem: CardItem<Habit>) {
            binding.apply {
                tvTitle.text = cardItem.title
                seeAllButton.setGradientColor(
                    intArrayOf(
                        itemView.context.getColor(
                            R.color.orange_start_color
                        ), itemView.context.getColor(R.color.orange_end_color)
                    )
                )
            }
            innerAdapter.submitList(cardItem.dataList)
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

    class ListItemDiffCallback : DiffUtil.ItemCallback<CardItem<Habit>>() {
        override fun areItemsTheSame(oldItem: CardItem<Habit>, newItem: CardItem<Habit>): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: CardItem<Habit>, newItem: CardItem<Habit>
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }
}
