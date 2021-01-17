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
}
