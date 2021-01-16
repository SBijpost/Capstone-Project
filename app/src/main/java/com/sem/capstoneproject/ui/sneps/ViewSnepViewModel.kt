package com.sem.capstoneproject.ui.sneps

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sem.capstoneproject.model.SnepItem

class ViewSnepViewModel : ViewModel() {
    private val mutableSelectedSnepItem = MutableLiveData<SnepItem>()
    val selectedSnepItem: LiveData<SnepItem> get() = mutableSelectedSnepItem

    fun selectData(snepItem: SnepItem) {
        mutableSelectedSnepItem.value = snepItem
    }
}