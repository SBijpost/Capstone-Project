package com.sem.capstoneproject.ui.sendsnep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sem.capstoneproject.model.Friend
import com.sem.capstoneproject.repository.ApiCallback
import com.sem.capstoneproject.repository.FriendsRepository
import kotlinx.coroutines.launch

class ReceiversViewModel : ViewModel(){
    private val friendsRepository = FriendsRepository()

    //use encapsulation to expose as LiveData
    val friends: LiveData<List<Friend>>
        get() = _friends

    private val _friends = MutableLiveData<List<Friend>>().apply {
        viewModelScope.launch {
            friendsRepository.getFriends(object : ApiCallback {
                override fun onSuccess(result: List<Friend>) {
                    value = result
                }
            })
        }
    }
}