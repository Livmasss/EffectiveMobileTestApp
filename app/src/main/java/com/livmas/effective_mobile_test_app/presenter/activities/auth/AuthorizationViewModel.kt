package com.livmas.effective_mobile_test_app.presenter.activities.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthorizationViewModel: ViewModel() {
    val name: LiveData<String>
        get() = _name
    private val  _name: MutableLiveData<String> by lazy {
        MutableLiveData("")
    }

    val lastname: LiveData<String>
        get() = _lastname
    private val _lastname: MutableLiveData<String> by lazy {
        MutableLiveData("")
    }

    val phone: LiveData<String>
        get() = _phone
    private val _phone: MutableLiveData<String> by lazy {
        MutableLiveData("")
    }

    val isButtonActive: MutableLiveData<Boolean> by lazy {
        MutableLiveData(false)
    }

    fun login(
    name: String,
    lastname: String,
    phone: String
    ) {
        _name.postValue(name)
        _lastname.postValue(lastname)
        _phone.postValue(phone)
    }
}