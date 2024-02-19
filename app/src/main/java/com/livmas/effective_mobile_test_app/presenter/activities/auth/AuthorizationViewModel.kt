package com.livmas.effective_mobile_test_app.presenter.activities.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.livmas.data.localDataBase.entities.UserEntity
import com.livmas.data.localDataBase.repositories.UserRepository
import org.koin.java.KoinJavaComponent.inject
import java.util.UUID

class AuthorizationViewModel: ViewModel() {
//    private val repository: UserRepository by inject(UserRepository::class.java)
//    val isAuthed: LiveData<Boolean>
//        get() = mutableIsAuthed
//    private val mutableIsAuthed: MutableLiveData<Boolean> by lazy {
//        MutableLiveData(checkAuthorization())
//    }

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

//        authorize(
//            UserEntity(
//            UUID.randomUUID(),
//            name,
//            lastname,
//            phone
//            )
//        )
    }

//    private fun authorize(user: UserEntity) {
//        repository.insertAuthedUser(user)
//    }
//
//    fun checkAuthorization(): Boolean =
//        repository.getAuthedUser() != null
}