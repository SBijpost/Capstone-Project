package com.sem.capstoneproject.ui.sneps

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.sem.capstoneproject.MainActivity
import com.sem.capstoneproject.R
import com.sem.capstoneproject.model.SnepItem
import com.sem.capstoneproject.tabs.TabsActivity
import com.sem.capstoneproject.ui.sendsnep.SendSnepActivity
import kotlinx.android.synthetic.main.fragment_create_snep.*
import kotlinx.android.synthetic.main.view_snep.*
import kotlinx.android.synthetic.main.view_snep.snepIV

class ViewSnepFragment: Fragment() {
    private lateinit var auth: FirebaseAuth

    private val viewModel: ViewSnepViewModel by activityViewModels()

    private var displayTime: Long = 10000L

    lateinit var countdown_timer: CountDownTimer
    var isRunning: Boolean = false;
    var time_in_milli_seconds = 0L

    private lateinit var snep: SnepItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth

        (activity as ViewSnepActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as ViewSnepActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        activity?.title = "View Snep"

        return inflater.inflate(R.layout.view_snep, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        startTimer(displayTime)

    }

    private fun initViews() {

        val storageRef = Firebase.storage.reference

        viewModel.selectedSnepItem.observe(this.viewLifecycleOwner, Observer { item ->
            snep = item
            snepTV.text = item.message
            storageRef.child(item.image_path).downloadUrl.addOnSuccessListener {
                Glide.with(this)
                    .load(it)
                    .into(snepIV)
            }
            displayTime = item.length.toLong()
        })
    }

    private fun startTimer(time_in_seconds: Long) {
        countdown_timer = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                stopViewingSnep()
            }

            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
                updateTextUI()
            }
        }
        countdown_timer.start()

        isRunning = true
    }

    private fun updateTextUI() {
        val seconds = (time_in_milli_seconds / 1000) % 60

        timeTV.text = "$seconds"
    }

    // go back to list and delete snep from list, database and storage
    private fun stopViewingSnep() {
        val database: DatabaseReference = FirebaseDatabase.getInstance().reference
        val myRef = database.child("users").child(auth.currentUser!!.uid)
        val storageRef = Firebase.storage.reference
        val snepRef = storageRef.child(snep.image_path)

        myRef.child("sneps").child(snep.id).removeValue().addOnSuccessListener {
            Log.d("ViewSnep", "Snep deleted from database")
        }
        snepRef.delete().addOnSuccessListener {
            Log.d("ViewSnep", "Snep deleted from storage")
        }

        val intent = Intent(this.requireContext(), TabsActivity::class.java)
        startActivity(intent)
    }

}