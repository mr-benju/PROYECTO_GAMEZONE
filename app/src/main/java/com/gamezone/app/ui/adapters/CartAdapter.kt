package com.gamezone.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gamezone.app.data.CartItem
import com.gamezone.app.databinding.ItemCartBinding

class CartAdapter(
    private val onRemove: (String) -> Unit,
    private val onQuantityChange: (String, Int) -> Unit
) : ListAdapter<CartItem, CartAdapter.CartViewHolder>(CartDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding, onRemove, onQuantityChange)
    }
    
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class CartViewHolder(
        private val binding: ItemCartBinding,
        private val onRemove: (String) -> Unit,
        private val onQuantityChange: (String, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: CartItem) {
            binding.itemTitle.text = item.game.title
            binding.itemPlatform.text = item.game.platform
            binding.itemPrice.text = String.format("$%.2f", item.game.price)
            binding.quantityText.text = item.quantity.toString()
            binding.subtotalText.text = String.format("$%.2f", item.subtotal)
            
            binding.removeButton.setOnClickListener {
                onRemove(item.game.id)
            }
            
            binding.decreaseButton.setOnClickListener {
                val newQuantity = item.quantity - 1
                if (newQuantity > 0) {
                    onQuantityChange(item.game.id, newQuantity)
                }
            }
            
            binding.increaseButton.setOnClickListener {
                onQuantityChange(item.game.id, item.quantity + 1)
            }
        }
    }
    
    private class CartDiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.game.id == newItem.game.id
        }
        
        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }
    }
}
