package com.sem.capstoneproject.ui.sneps

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
import com.sem.capstoneproject.adapter.SnepAdapter
import com.sem.capstoneproject.model.SnepItem
import com.sem.capstoneproject.ui.sendsnep.SendSnepActivity
import kotlinx.android.synthetic.main.fragment_create_snep.*
import kotlinx.android.synthetic.main.fragment_sneps_list.*

class SnepsListFragment: Fragment() {
    private lateinit var auth: FirebaseAuth

    private lateinit var snepAdapter: SnepAdapter
    private val viewModel: SnepViewModel by viewModels()
    private val sneps = arrayListOf<SnepItem>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth

        return inflater.inflate(R.layout.fragment_sneps_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            signOutUser()
        }

        snepAdapter = SnepAdapter(sneps, ::onSnepClick)
//        rvSneps.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//        rvSneps.adapter = snepAdapter

        initViews()
        observeSneps()
    }

    private fun initViews() {
        rvSneps.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvSneps.adapter = snepAdapter
    }

    private fun observeSneps() {
        viewModel.snepItems.observe(viewLifecycleOwner, Observer {
            sneps.clear()
            sneps.addAll(it)
            snepAdapter.notifyDataSetChanged()
        })
    }

    private fun onSnepClick(snepItem: SnepItem) {
        Snackbar.make(rvSneps, snepItem.image_path, Snackbar.LENGTH_LONG).show()
//        val bundle = bundleOf("message" to snepItem.message, "duration" to snepItem.length, "image" to snepItem.image_path)
//        findNavController().navigate(R.id.action_snepsListFragment_to_viewSnepFragment, bundle)
        val intent = Intent(this.requireContext(), ViewSnepActivity::class.java)
//        intent.putExtra("uri", uri)
        startActivity(intent)
    }

    private fun signOutUser() {
        auth.signOut()
        val intent = Intent(this.requireContext(), MainActivity::class.java)
        startActivity(intent)
    }



}