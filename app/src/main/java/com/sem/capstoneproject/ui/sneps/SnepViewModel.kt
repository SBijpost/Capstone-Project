package com.sem.capstoneproject.ui.sneps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sem.capstoneproject.model.SnepItem
import com.sem.capstoneproject.repository.ApiCallbackSnep
import com.sem.capstoneproject.repository.SnepRepository
import kotlinx.coroutines.launch

class SnepViewModel : ViewModel(){
    private val snepRepository = SnepRepository()

    //use encapsulation to expose as LiveData
    val snepItems: LiveData<List<SnepItem>>
        get() = _snepItems

    private val _snepItems = MutableLiveData<List<SnepItem>>().apply {
        viewModelScope.launch {
            snepRepository.getSnepItems(object: ApiCallbackSnep {
                override fun onSuccess(result: List<SnepItem>) {
                    value = result
                }
            })
        }
    }
}