package com.vt.avowsgardaortotest.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vt.avowsgardaortotest.data.domain.model.Pokemon
import com.vt.avowsgardaortotest.databinding.ItemPokemonBinding

class HomeAdapter : ListAdapter<Pokemon, HomeAdapter.ViewHolder>(DIFF_CALLBACK) {
    var onClickListener: ((Pokemon) -> Unit)? = null
    //    var lastPosition = -1
    var selectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.bindTo(data)
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class ViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(pokemon: Pokemon) {
            val adapterPositionId = currentList[adapterPosition].id
            with(binding) {
                title.text = pokemon.name
            }
            if (selectedItem == adapterPositionId) {
                binding.radioButton.isChecked = true
            } else {
                binding.radioButton.isChecked = false
                binding.container.setBackgroundColor(Color.WHITE)
            }
        }

        init {
            itemView.setOnClickListener {
                selectedItem = currentList[adapterPosition].id
                onClickListener?.invoke(currentList[adapterPosition])
                notifyDataSetChanged()
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Pokemon> =
            object : DiffUtil.ItemCallback<Pokemon>() {
                override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                    return oldItem.url == newItem.url
                }

                override fun areContentsTheSame(
                    oldItem: Pokemon,
                    newItem: Pokemon
                ): Boolean {
                    return oldItem == newItem
                }
            }

    }
}