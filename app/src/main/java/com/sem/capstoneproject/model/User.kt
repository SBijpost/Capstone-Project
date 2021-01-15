package com.sem.capstoneproject.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var fullname: String? = "",
    var email: String? = ""
)