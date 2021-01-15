package com.sem.capstoneproject.ui.sendsnep

import android.R.attr.outAnimation
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.sem.capstoneproject.R
import com.sem.capstoneproject.adapter.FriendsAdapter
import com.sem.capstoneproject.model.Friend
import com.sem.capstoneproject.model.SnepItem
import com.sem.capstoneproject.tabs.TabsActivity
import kotlinx.android.synthetic.main.fragment_create_snep.*
import kotlinx.android.synthetic.main.fragment_create_snep.sendButton
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_select_receiver.*
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


class SelectReceiverFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private val viewModel: ReceiversViewModel by viewModels()
    private val imageViewModel: ImageViewModel by activityViewModels()

    private val friends = arrayListOf<Friend>()
    private lateinit var friendsAdapter: FriendsAdapter

    private lateinit var message: String

    private var snepImage: Uri = Uri.EMPTY

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auth = Firebase.auth

        (activity as SendSnepActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as SendSnepActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        activity?.title = "Select Recipients"

        imageViewModel.selectedUri.observe(this.viewLifecycleOwner, Observer { uri ->
            snepImage = uri
        })

        return inflater.inflate(R.layout.fragment_select_receiver, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        message = arguments?.getString("message").toString()

        friendsAdapter = FriendsAdapter(friends, this.requireContext())
        rvSelect.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvSelect.adapter = friendsAdapter

        sendButton.setOnClickListener {
            uploadSnapImage(friendsAdapter.checkedFriends)
        }

        initViews()
        observeColors()
    }

    private fun initViews() {
        rvSelect.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvSelect.adapter = friendsAdapter
    }

    private fun observeColors() {
        viewModel.friends.observe(viewLifecycleOwner, Observer {
            friends.clear()
            friends.addAll(it)
            friendsAdapter.notifyDataSetChanged()
        })
    }

    private fun uploadSnapImage(friends: ArrayList<Friend>) {
        val storageRef = Firebase.storage.reference
        val selectedFriends = friends
        val file = Uri.fromFile(File(snepImage.path.toString()))
        val id = UUID.randomUUID().toString()
        val riversRef = storageRef.child("snaps/$id")
        val uploadTask = riversRef.putFile(file)
        val database: DatabaseReference = FirebaseDatabase.getInstance().reference
        val myRef = database.child("users")

        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
            Toast.makeText(this.requireContext(), "Sending snap failed", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            selectedFriends.forEach {
                myRef.child(it.uid.toString()).child("snaps").child(id).setValue(SnepItem(id, arguments?.getString("message").toString(),
                taskSnapshot.storage.path, auth.currentUser?.uid.toString(), arguments?.getInt("duration"),false))
            }
            val intent = Intent(this.requireContext(), TabsActivity::class.java)
            startActivity(intent)
        }


    }

//    private fun onColorClick(friend: Friend) {
//        Snackbar.make(rvColors, "This color is: ${colorItem.name}", Snackbar.LENGTH_LONG).show()
//    }
}