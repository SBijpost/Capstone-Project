package com.sem.capstoneproject.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Friend(
    var uid: String? = "",
    var name: String? = "",
    var selected: Boolean = false
) {}
