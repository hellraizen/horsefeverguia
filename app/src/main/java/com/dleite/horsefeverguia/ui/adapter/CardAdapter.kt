package com.dleite.horsefeverguia.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dleite.horsefeverguia.databinding.ListItemBinding
import com.dleite.horsefeverguia.ui.models.CardHorse

class CardAdapter  : ListAdapter<CardHorse, CardAdapter.UsersViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class UsersViewHolder(
        private val itemBinding: ListItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(cardHorse: CardHorse) {
            itemBinding.run {
                Glide.with(itemView)
                    .load(cardHorse.img)
                    .fitCenter()
                    .into(picture)
                title.text = cardHorse.title
            }
        }

        companion object {
            fun create(parent: ViewGroup): UsersViewHolder {
                val itemBinding = ListItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return UsersViewHolder(itemBinding)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CardHorse>() {
            override fun areItemsTheSame(
                oldItem: CardHorse,
                newItem: CardHorse
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: CardHorse,
                newItem: CardHorse
            ): Boolean {
                return oldItem == newItem
            }

        }
    }


}