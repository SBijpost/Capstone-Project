package com.sem.capstoneproject.ui.sendsnep

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sem.capstoneproject.MainActivity
import com.sem.capstoneproject.R
import kotlinx.android.synthetic.main.fragment_create_snep.*
import kotlinx.android.synthetic.main.fragment_login.*

class CreateSnepFragment : Fragment() {

    private val viewModel: ImageViewModel by activityViewModels()

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auth = Firebase.auth

        (activity as SendSnepActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as SendSnepActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        activity?.title = "Create Snep"

        viewModel.selectedUri.observe(this.viewLifecycleOwner, Observer { uri ->
            snepIV.setImageURI(uri)
        })

        return inflater.inflate(R.layout.fragment_create_snep, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendButton.setOnClickListener {
            if(tietMessage.text.isNullOrBlank()) {
                Snackbar.make(view, "Message can't be empty", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
            } else {
                val bundle = bundleOf("message" to tietMessage.text.toString(), "duration" to slider.value.toInt())
                findNavController().navigate(R.id.action_createSnepFragment_to_selectReceiverFragment, bundle)
            }
        }
    }
}