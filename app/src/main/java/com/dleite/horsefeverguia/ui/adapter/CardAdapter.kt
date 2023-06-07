package com.dleite.horsefeverguia.ui.adapter

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dleite.horsefeverguia.databinding.ListItemBinding
import com.dleite.horsefeverguia.ui.models.CardHorse

class CardAdapter(
    private var cardHorse: List<CardHorse>,
    private val onItemClickListener: (cardHorse: CardHorse) -> Unit
) : RecyclerView.Adapter<CardAdapter.CardsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val itemBinding = ListItemBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CardsViewHolder(itemBinding,onItemClickListener)
    }
    fun setFilteredList(cardHorse: List<CardHorse>){
        this.cardHorse = cardHorse
        notifyDataSetChanged()

    }


    override fun getItemCount(): Int = cardHorse.size


    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        val card = cardHorse[position]
        holder.bind(card)

    }


    class CardsViewHolder(
        private val itemBinding: ListItemBinding,
        private val onItemClickListener: (cardHorse: CardHorse) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(cardHorse: CardHorse) {
            with(itemBinding) {
                com.bumptech.glide.Glide.with(picture)
                    .load(cardHorse.img)
                    .fitCenter()
                    .into(picture)

                title.text = cardHorse.title
                this.root.setOnClickListener { onItemClickListener.invoke(cardHorse) }
            }
        }
    }


}