package com.sem.capstoneproject.ui.sendsnep

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sem.capstoneproject.R
import com.sem.capstoneproject.tabs.TabsActivity

class SendSnepActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val viewModel: ImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        setContentView(R.layout.activity_send_snep)

        viewModel.selectUri(Uri.parse(intent.getStringExtra("uri")))
    }

    override fun onSupportNavigateUp(): Boolean {
        if(findNavController(R.id.nav_create_fragment).currentDestination?.id == findNavController(R.id.nav_create_fragment).graph.startDestination) {
            val intent = Intent(this, TabsActivity::class.java)
            startActivity(intent)
        } else {
            findNavController(R.id.nav_create_fragment).navigateUp()
        }

        return super.onSupportNavigateUp()
    }
}