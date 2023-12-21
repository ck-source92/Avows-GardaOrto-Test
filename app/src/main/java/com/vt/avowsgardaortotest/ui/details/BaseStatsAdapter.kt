package com.vt.avowsgardaortotest.ui.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vt.avowsgardaortotest.data.dto.StatsItem
import com.vt.avowsgardaortotest.databinding.ItemStatsBinding

class BaseStatsAdapter : ListAdapter<StatsItem, BaseStatsAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemStatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseStatsAdapter.ViewHolder, position: Int) {
        val data = getItem(position)
        holder.bindTo(data)
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class ViewHolder(private val binding: ItemStatsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(stats: StatsItem) {
            with(binding) {
                name.text = stats.stat.name
                baseStats.text = stats.baseStat.toString()
            }
        }
    }


    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<StatsItem> =
            object : DiffUtil.ItemCallback<StatsItem>() {
                override fun areItemsTheSame(
                    oldItem: StatsItem,
                    newItem: StatsItem
                ): Boolean {
                    return oldItem.stat.url == newItem.stat.url
                }

                override fun areContentsTheSame(
                    oldItem: StatsItem,
                    newItem: StatsItem
                ): Boolean {
                    return oldItem == newItem
                }
            }

    }
}