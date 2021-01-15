package com.sem.capstoneproject.repository

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.sem.capstoneproject.model.Friend


class FriendsRepository {

    private var friends = mutableListOf<Friend>()

    fun getFriends(callback: ApiCallback) {

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
        ref.addListenerForSingleValueEvent(menuListener)
    }

    // ?
    private fun collectNames(users: Map<String?, Any?>?) {
        val names: ArrayList<String?> = ArrayList()

        //iterate through each user, ignoring their UID
        if (users != null) {
            for ((_, value) in users) {

                //Get user map
                val singleUser = value as Map<*, *>
                //Get phone field and append to list
                Log.d("fasUDDUDU", singleUser["username"].toString())
                val friend: Friend = Friend(value as String, singleUser["username"] as String)
                friends.add(friend)
            }
        }
        //System.out.println(names.toString())
    }

}

interface ApiCallback {
    fun onSuccess(result: List<Friend>)
}