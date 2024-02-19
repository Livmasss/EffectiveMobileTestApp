package com.livmas.effective_mobile_test_app.presenter.activities.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.domain.models.UserModel
import com.livmas.domain.usecases.user.LoginUseCase
import com.livmas.domain.usecases.user.TryReloginUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.UUID

class AuthorizationViewModel: ViewModel() {
    init {
        checkAuthorization()
    }

    private val reloginUseCase = TryReloginUseCase()

    val isAuthed: LiveData<Boolean?>
        get() = mutableIsAuthed
    private val mutableIsAuthed: MutableLiveData<Boolean?> by lazy {
        MutableLiveData()
    }

    val name: LiveData<String>
        get() = _name
    private val  _name: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    val lastname: LiveData<String>
        get() = _lastname
    private val _lastname: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    val phone: LiveData<String>
        get() = _phone
    private val _phone: MutableLiveData<String> by lazy {
        MutableLiveData()
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

        val user =
            UserModel(
                UUID.randomUUID(),
                name,
                lastname,
                phone
            )
        authorize(user)
    }

    private fun authorize(user: UserModel) {
        CoroutineScope(Dispatchers.IO).launch {
            LoginUseCase(user).execute()
        }
    }

    private fun checkAuthorization() {
        CoroutineScope(Dispatchers.IO).launch {
            var result: Boolean
            runBlocking {
                result = reloginUseCase.execute()
                mutableIsAuthed.postValue(result)
            }
        }
    }
}