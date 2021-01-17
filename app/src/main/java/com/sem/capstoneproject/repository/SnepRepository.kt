package com.sem.capstoneproject.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.sem.capstoneproject.model.SnepItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SnepRepository {

    private var sneps = mutableListOf<SnepItem>()
    private var auth = Firebase.auth


    suspend fun getSnepItems(callback: ApiCallbackSnep) {

        val userId = auth.currentUser!!.uid

        val ref = FirebaseDatabase.getInstance().getReference("users").child(userId).child("sneps")

        val snepListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dsp in dataSnapshot.children) {
                    val snep = SnepItem(dsp.child("id").value.toString(), dsp.child("message").value.toString(),
                        dsp.child("image_path").value.toString(), dsp.child("senderId").value.toString(), dsp.child("senderName").value.toString(),
                        dsp.child("length").value.toString().toInt(), false)
                    sneps.add(snep) //add result into array list
                }
                callback.onSuccess(sneps)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
        }
        withContext(Dispatchers.IO){
            ref.addListenerForSingleValueEvent(snepListener)
        }

    }

}

interface ApiCallbackSnep {
    fun onSuccess(result: List<SnepItem>)
}