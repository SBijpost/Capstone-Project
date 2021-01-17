package com.sem.capstoneproject.ui.friends

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mancj.materialsearchbar.MaterialSearchBar
import com.sem.capstoneproject.R
import com.sem.capstoneproject.adapter.FriendsAdapter
import com.sem.capstoneproject.model.Friend
import kotlinx.android.synthetic.main.fragment_friends.*

class FriendsFragment: Fragment(), MaterialSearchBar.OnSearchActionListener {
    private lateinit var auth: FirebaseAuth

    private val friends = arrayListOf<Friend>()
    private lateinit var friendsAdapter: FriendsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auth = Firebase.auth

        return inflater.inflate(R.layout.fragment_friends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBar.setPlaceHolder("Search")
        searchBar.setHint("Search for friends");
        searchBar.setOnSearchActionListener(this)

        friendsAdapter = FriendsAdapter(friends, ::onFriendClick)

        initViews()
        addDummyFriends()
    }

    private fun initViews() {
        rvFriends.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvFriends.adapter = friendsAdapter
    }

    private fun addDummyFriends() {
        friends.add(Friend("395283580238050", "Henk Bakker", false))
        friends.add(Friend("39asfs80jjk50", "Klaas Janssen", false))
        friends.add(Friend("392323238050", "Geert Janssen", false))
        friends.add(Friend("39343438050", "Pieter Post", false))
        friends.add(Friend("39523443348050", "Frans Duijts", false))
        friends.add(Friend("3232323283580238050", "Mark Rutte", false))
    }

    private fun onFriendClick(friend: Friend) {
        Snackbar.make(rvFriends, "Added " + friend.name.toString() + "!", Snackbar.LENGTH_LONG).show()
    }

    override fun onSearchStateChanged(p0: Boolean) {
        val state = if (p0) "enabled" else "disabled"
        Log.d("SearchBar", "Search $state")
    }

    override fun onSearchConfirmed(p0: CharSequence?) {
        Toast.makeText(this.requireContext(),"Searching "+ p0.toString()+" ......", Toast.LENGTH_SHORT).show();
    }

    override fun onButtonClicked(p0: Int) {
        when (p0){
            MaterialSearchBar.BUTTON_NAVIGATION -> Toast.makeText(this.requireContext(), "Button Nav " , Toast.LENGTH_SHORT).show();
            MaterialSearchBar.BUTTON_SPEECH -> Toast.makeText(this.requireContext(), "Speech " , Toast.LENGTH_SHORT).show();
        }
    }

}