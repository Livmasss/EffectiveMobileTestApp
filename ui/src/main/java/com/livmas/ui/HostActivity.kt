package com.livmas.ui

import androidx.lifecycle.MutableLiveData

interface HostActivity {
    val pageTitle: MutableLiveData<String>
}