package com.gamezone.app.data

data class CartItem(
    val game: Game,
    var quantity: Int = 1
) {
    val subtotal: Double
        get() = game.price * quantity
}
