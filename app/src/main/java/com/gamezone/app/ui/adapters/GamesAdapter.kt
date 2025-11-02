package com.gamezone.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gamezone.app.data.Game
import com.gamezone.app.databinding.ItemGameBinding

class GamesAdapter(
    private val onAddToCart: (Game) -> Unit
) : ListAdapter<Game, GamesAdapter.GameViewHolder>(GameDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGameBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GameViewHolder(binding, onAddToCart)
    }
    
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class GameViewHolder(
        private val binding: ItemGameBinding,
        private val onAddToCart: (Game) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(game: Game) {
            binding.gameTitle.text = game.title
            binding.gamePlatform.text = game.platform
            binding.gameDescription.text = game.description
            binding.gameRating.text = game.rating.toString()
            binding.gameCategory.text = game.category
            binding.gamePrice.text = String.format("$%.2f", game.price)
            
            binding.addToCartButton.setOnClickListener {
                onAddToCart(game)
            }
        }
    }
    
    private class GameDiffCallback : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem == newItem
        }
    }
}
