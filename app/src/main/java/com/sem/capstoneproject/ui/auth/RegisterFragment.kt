package com.sem.capstoneproject.ui.auth

import android.content.ContentValues.TAG
import android.content.Intent
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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sem.capstoneproject.MainActivity
import com.sem.capstoneproject.R
import com.sem.capstoneproject.model.User
import com.sem.capstoneproject.tabs.TabsActivity
import kotlinx.android.synthetic.main.fragment_register.registerButton
import kotlinx.android.synthetic.main.fragment_register.tietEmail
import kotlinx.android.synthetic.main.fragment_register.tietPassword
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        auth = Firebase.auth
        database = Firebase.database

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
                                writeUserToDatabase(view, user.uid, user.displayName, user.email)
                                Snackbar.make(view, "Successfully registered!", Snackbar.LENGTH_SHORT)
                                        .setAction("Action", null).show()
                                updateUI()
                            }
                        }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signUpWithEmail:failure", task.exception)
                    Snackbar.make(view, "Registration failed. Make sure the passwords are identical.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                    //updateUI(null)
                }
            }
    }

    private fun writeUserToDatabase(view: View, userId: String, name: String?, email: String?) {
        val user = User(name, email)
        val database: DatabaseReference = FirebaseDatabase.getInstance().reference
        val myRef = database.child("users")

        myRef.child(userId).setValue(user)

        Log.w(TAG, "writeToDatabase:success")
    }

    private fun updateUI() {
        val intent = Intent(this.requireContext(), TabsActivity::class.java)
        startActivity(intent)
    }

}