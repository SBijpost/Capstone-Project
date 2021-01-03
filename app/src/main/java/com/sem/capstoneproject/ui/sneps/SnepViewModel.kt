package com.sem.capstoneproject.ui.sneps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sem.capstoneproject.model.SnepItem
import com.sem.capstoneproject.repository.ApiCallback
import com.sem.capstoneproject.repository.SnepRepository

class SnepViewModel : ViewModel(){
    private val snepRepository = SnepRepository()

    //use encapsulation to expose as LiveData
    val snepItems: LiveData<List<SnepItem>>
        get() = _snepItems

    private val _snepItems = MutableLiveData<List<SnepItem>>().apply {
        value = snepRepository.getSnepItems()
    }
}