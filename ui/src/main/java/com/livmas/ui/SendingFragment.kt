package com.livmas.ui

import androidx.fragment.app.Fragment

abstract class SendingFragment: Fragment() {
    protected var title: String? = null
    override fun onStart() {
        super.onStart()

        val activity = requireActivity() as HostActivity
        activity.pageTitle.postValue(title)
    }
}