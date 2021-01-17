package com.sem.capstoneproject.repository

import com.google.firebase.database.*
import com.sem.capstoneproject.model.Friend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FriendsRepository {

    private var friends = mutableListOf<Friend>()

    suspend fun getFriends(callback: ApiCallback) {

        val ref = FirebaseDatabase.getInstance().getReference("users")


        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dsp in dataSnapshot.children) {
                    val friend = Friend(dsp.key, dsp.child("username").value.toString())
                    friends.add(friend) //add result into array list
                }
                callback.onSuccess(friends)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
        }
        withContext(Dispatchers.IO) {
            ref.addListenerForSingleValueEvent(menuListener)
        }
    }

}

interface ApiCallback {
    fun onSuccess(result: List<Friend>)
}