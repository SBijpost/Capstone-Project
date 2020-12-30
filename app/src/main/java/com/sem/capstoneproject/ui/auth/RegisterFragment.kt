package com.sem.capstoneproject.ui.auth

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.sem.capstoneproject.MainActivity
import com.sem.capstoneproject.R
import kotlinx.android.synthetic.main.fragment_register.registerButton
import kotlinx.android.synthetic.main.fragment_register.tietEmail
import kotlinx.android.synthetic.main.fragment_register.tietPassword
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        auth = Firebase.auth

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        activity?.title = "Register"

        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        registerButton.setOnClickListener {
            if(tietEmail.text.isNullOrBlank() or tietPassword.text.isNullOrBlank() or tietName.text.isNullOrBlank() or tietPasswordConf.text.isNullOrBlank()) {
                Snackbar.make(view, "Please fill in both fields.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            } else {
                if (tietPassword.text.toString() == tietPasswordConf.text.toString()) {
                    signUpUser(view, tietEmail.text.toString(), tietPassword.text.toString(), tietName.text.toString())
                } else {
                    Snackbar.make(view, "Passwords are not identical.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun signUpUser(view: View, email: String, password: String, name: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signUpWithEmail:success")
                    val user = auth.currentUser
                    val profileUpdate = userProfileChangeRequest {
                        displayName = name
                    }
                    user!!.updateProfile(profileUpdate)
                        .addOnCompleteListener { task2 ->
                            if (task2.isSuccessful) {
                                Log.d(TAG, "User profile updated.")
                            }
                        }
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signUpWithEmail:failure", task.exception)
                    Snackbar.make(view, "Authentication failed.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                    //updateUI(null)
                }
            }
    }

}