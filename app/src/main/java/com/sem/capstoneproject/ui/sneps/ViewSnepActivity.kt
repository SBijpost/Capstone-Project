package com.sem.capstoneproject.ui.sneps

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sem.capstoneproject.R
import com.sem.capstoneproject.model.SnepItem
import com.sem.capstoneproject.tabs.TabsActivity
import com.sem.capstoneproject.ui.sendsnep.ImageViewModel

class ViewSnepActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val viewModel: ViewSnepViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        setContentView(R.layout.activity_view_snep)

        viewModel.selectData(intent.getSerializableExtra("snepItem") as SnepItem)
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, TabsActivity::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }
}