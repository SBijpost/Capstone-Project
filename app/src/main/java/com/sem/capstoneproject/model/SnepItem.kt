package com.sem.capstoneproject.model

data class SnepItem(

    val id: String,
    val message: String,
    val image_path: String,
    val sender: String,
    val length: Int?,
    val opened: Boolean

    ) {
//    fun getImageUrl() = "https://image.tmdb.org/t/p/w500/$poster_path"
//    fun getBackdropImage() = "https://image.tmdb.org/t/p/original/$backdrop_path"
}
