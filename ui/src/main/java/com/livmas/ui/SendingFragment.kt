package com.livmas.ui

import androidx.fragment.app.Fragment
import androidx.navigation.NavController

abstract class SendingFragment: Fragment() {
    protected var title: String? = null
    protected var navController: NavController? = null
    override fun onStart() {
        super.onStart()

        val activity = requireActivity() as HostActivity
        navController = activity.navController
        activity.pageTitle.postValue(title)
    }
}