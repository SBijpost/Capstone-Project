package com.sem.capstoneproject.ui.sneps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sem.capstoneproject.model.SnepItem
import com.sem.capstoneproject.repository.ApiCallback
import com.sem.capstoneproject.repository.SnepRepository

class SnepViewModel(uid: String) : ViewModel(){
    private val snepRepository = SnepRepository()

    var uidParam = uid

    //use encapsulation to expose as LiveData
    val snepItems: LiveData<List<SnepItem>>
        get() = _snepItems(uidParam)

    private fun _snepItems(uid: String) = MutableLiveData<List<SnepItem>>().apply {
        snepRepository.getSnepItems(uid, object : ApiCallback {
            override fun onSuccess(result: List<SnepItem>) {
                value = result
            }
        })
    }
}