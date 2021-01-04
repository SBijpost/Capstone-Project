package com.sem.capstoneproject.ui.friends

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sem.capstoneproject.R
import com.sem.capstoneproject.tabs.TabsActivity
import com.sem.capstoneproject.ui.sendsnep.ImageViewModel

class FriendsActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

//    private val viewModel: ImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        setContentView(R.layout.activity_friends)

//        viewModel.selectUri(Uri.parse(intent.getStringExtra("uri")))
    }

    override fun onSupportNavigateUp(): Boolean {
//        if(findNavController(R.id.nav_create_fragment).currentDestination?.id == findNavController(R.id.nav_create_fragment).graph.startDestination) {
//            val intent = Intent(this, TabsActivity::class.java)
//            startActivity(intent)
//        } else {
//            findNavController(R.id.nav_create_fragment).navigateUp()
//        }

        return super.onSupportNavigateUp()
    }
}