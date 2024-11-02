package com.example.simpleuserprofilefetcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserProfileViewModel : ViewModel() {
    private val userProfileRepository = UserProfileRepository()
    private val _userProfile = MutableLiveData<UserProfile?>()
    val userProfile: LiveData<UserProfile?> get() = _userProfile

    fun loadUserProfile() {
        userProfileRepository.fetchUserProfile { profile ->
            _userProfile.postValue(profile)
        }
    }
}
