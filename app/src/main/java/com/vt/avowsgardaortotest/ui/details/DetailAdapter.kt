package com.vt.avowsgardaortotest.ui.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vt.avowsgardaortotest.data.dto.AbilitiesItem
import com.vt.avowsgardaortotest.databinding.ItemAbilityBinding

class DetailAdapter : ListAdapter<AbilitiesItem, DetailAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemAbilityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.bindTo(data)
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class ViewHolder(private val binding: ItemAbilityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(ability: AbilitiesItem) {
            with(binding) {
                abilityText.text = ability.ability.name
            }
        }
    }


    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<AbilitiesItem> =
            object : DiffUtil.ItemCallback<AbilitiesItem>() {
                override fun areItemsTheSame(
                    oldItem: AbilitiesItem,
                    newItem: AbilitiesItem
                ): Boolean {
                    return oldItem.ability == newItem.ability
                }

                override fun areContentsTheSame(
                    oldItem: AbilitiesItem,
                    newItem: AbilitiesItem
                ): Boolean {
                    return oldItem == newItem
                }
            }

    }
}