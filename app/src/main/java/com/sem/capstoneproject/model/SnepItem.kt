package com.sem.capstoneproject.model

data class SnepItem(

    val id: Int,
    val message: String,
    val date: String,
    val image_path: String,
    val receiver: String,
    val sender: String,
    val opened: Boolean

    ) {
//    fun getImageUrl() = "https://image.tmdb.org/t/p/w500/$poster_path"
//    fun getBackdropImage() = "https://image.tmdb.org/t/p/original/$backdrop_path"
}
