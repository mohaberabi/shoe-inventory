package com.example.shoestore

data class ShoeModel(
    val id: String,
    val name: String,
    val qty: Int,
    val price: Double,
    val company: String,
    val size: String,

    val describtion: String,
) {
    companion object {
        val empty = ShoeModel("", "", -1, 0.0, "", "", "")
    }
}


