package com.sem.capstoneproject.repository

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.sem.capstoneproject.model.SnepItem

class SnepRepository {

    private var sneps = listOf<SnepItem>()
    private var auth = Firebase.auth


    fun getSnepItems() : List<SnepItem> {

        val userId = auth.currentUser!!.uid

        val database: DatabaseReference = FirebaseDatabase.getInstance().reference
        val myRef = database.child("users")

//        val snepListener = object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//            }
//            override fun onCancelled(databaseError: DatabaseError) {
//                // handle error
//            }
//        }
//        myRef.addListenerForSingleValueEvent(snepListener)
//
//        myRef.child(userId).child("snaps").get()

        return arrayListOf()

    }

}

interface ApiCallback {
    fun onSuccess(result: List<SnepItem>)
}