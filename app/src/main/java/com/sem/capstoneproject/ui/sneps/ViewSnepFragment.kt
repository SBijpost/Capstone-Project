package com.sem.capstoneproject.ui.sneps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sem.capstoneproject.R

class ViewSnepFragment: Fragment() {
    private lateinit var auth: FirebaseAuth

    //private val viewModel: SnepViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth

        return inflater.inflate(R.layout.view_snep, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //observeSneps()
    }

//    private fun initViews() {
//        rvSneps.layoutManager =
//            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//        rvSneps.adapter = snepAdapter
//    }

//    private fun observeSneps() {
//        viewModel.snepItems.observe(viewLifecycleOwner, Observer {
//            sneps.clear()
//            sneps.addAll(it)
//            snepAdapter.notifyDataSetChanged()
//        })
//    }





}