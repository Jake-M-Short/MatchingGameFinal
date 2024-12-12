package com.example.matchinggame.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.matchinggame.R
import com.example.matchinggame.data.Card
import com.example.matchinggame.databinding.CardItemBinding

class GameBoardAdapter(
    private val onCardClick: (Card) -> Unit
) : RecyclerView.Adapter<GameBoardAdapter.CardViewHolder>() {

    private var cards: List<Card> = emptyList()

    fun submitList(newCards: List<Card>) {
        cards = newCards
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]
        holder.bind(card)
        holder.itemView.setOnClickListener {
            onCardClick(card)
        }
    }

    override fun getItemCount(): Int = cards.size

    class CardViewHolder(private val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card) {
            // Display the cards correctly
            if (card.isFlipped) {
                binding.cardImageView.setImageResource(card.imageResId) // Show the front of the card
            } else {
                binding.cardImageView.setImageResource(R.drawable.card) // Show the back of the card
            }
        }
    }

}

