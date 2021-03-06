package com.sem.capstoneproject.ui.auth

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sem.capstoneproject.MainActivity
import com.sem.capstoneproject.R
import com.sem.capstoneproject.tabs.TabsActivity
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auth = Firebase.auth

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        activity?.title = "Login"

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerNavButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        loginButton.setOnClickListener {
            if(tietEmail.text.isNullOrBlank() or tietPassword.text.isNullOrBlank()) {
                Snackbar.make(view, "Please fill in both fields.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            } else {
                signInUser(view, tietEmail.text.toString(), tietPassword.text.toString())
            }
        }
    }

    private fun signInUser(view: View, email: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")
                        Snackbar.make(view, "Successfully logged in!", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show()
                        updateUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Snackbar.make(view, "Authentication failed.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }
                }
        }
    }

    private fun updateUI() {
        val intent = Intent(this.requireContext(), TabsActivity::class.java)
        startActivity(intent)
    }



}