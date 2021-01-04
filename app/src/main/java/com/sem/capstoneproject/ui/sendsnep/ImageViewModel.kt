package com.sem.capstoneproject.ui.sendsnep

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageViewModel : ViewModel() {
    private val mutableSelectedUri = MutableLiveData<Uri>()
    val selectedUri: LiveData<Uri> get() = mutableSelectedUri

    fun selectUri(uri: Uri) {
        mutableSelectedUri.value = uri
    }
}