package com.livmas.ui.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.livmas.ui.HostActivity

abstract class SendingFragment: Fragment() {
    protected var title: String? = null
    protected var navController: NavController? = null
    private lateinit var activity: HostActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = requireActivity() as HostActivity
    }
    override fun onStart() {
        super.onStart()

        navController = activity.navController
        activity.pageTitle.postValue(title)
    }

    protected fun hideActivityTitle() {
        activity.showTitle.postValue(false)
    }

    protected fun showActivityTitle() {
        activity.showTitle.postValue(true)
    }
}