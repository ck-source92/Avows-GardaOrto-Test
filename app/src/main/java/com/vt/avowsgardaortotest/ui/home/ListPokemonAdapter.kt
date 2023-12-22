package com.vt.avowsgardaortotest.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vt.avowsgardaortotest.data.domain.model.Pokemon
import com.vt.avowsgardaortotest.databinding.ItemPokemonBinding

class ListPokemonAdapter : RecyclerView.Adapter<ListPokemonAdapter.PokemonViewHolder>() {

    private val listPokemon = ArrayList<Pokemon>()
    var onClickListener: ((Pokemon) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(pokemonList: List<Pokemon>?) {
        if (pokemonList == null) return
        listPokemon.clear()
        listPokemon.addAll(pokemonList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view =
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val data = listPokemon[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listPokemon.size
    }

    inner class PokemonViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Pokemon) {
            with(binding) {
                title.text = data.name
            }
        }

        init {
            itemView.setOnClickListener {
                onClickListener?.invoke(listPokemon[adapterPosition])
            }
        }

    }
}