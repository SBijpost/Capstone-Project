package com.sem.capstoneproject.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sem.capstoneproject.R
import com.sem.capstoneproject.tabs.TabsActivity
import kotlinx.android.synthetic.main.fragment_friends.*

class FriendsFragment: Fragment() {
    private lateinit var auth: FirebaseAuth

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
        fab.setOnClickListener {
            //(activity as TabsActivity?)!!.replaceFragment()
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            val fragment = AddFriendsFragment()
            fragmentTransaction.add(R.id.frame_layout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.hide(this)
            fragmentTransaction.commit()
        }
    }

}