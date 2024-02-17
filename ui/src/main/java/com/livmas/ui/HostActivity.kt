package com.livmas.ui

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController

interface HostActivity {
    val pageTitle: MutableLiveData<String>
    val navController: NavController
}