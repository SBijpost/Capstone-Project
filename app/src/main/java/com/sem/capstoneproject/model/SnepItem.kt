package com.sem.capstoneproject.model

import java.io.Serializable

data class SnepItem(

    val id: String,
    val message: String,
    val image_path: String,
    val senderId: String,
    val senderName: String,
    val length: Int,
    val opened: Boolean

    ): Serializable {
//    fun getImageUrl() = "https://image.tmdb.org/t/p/w500/$poster_path"
//    fun getBackdropImage() = "https://image.tmdb.org/t/p/original/$backdrop_path"
}
