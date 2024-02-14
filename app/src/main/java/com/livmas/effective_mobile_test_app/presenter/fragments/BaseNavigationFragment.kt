package com.livmas.effective_mobile_test_app.presenter.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.livmas.effective_mobile_test_app.presenter.NAVIGATION_TAG
import com.livmas.effective_mobile_test_app.presenter.activities.MainActivity

abstract class BaseNavigationFragment: Fragment() {
    protected lateinit var mainActivity: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(NAVIGATION_TAG, "Base navigation onViewCreated")
        mainActivity = activity as MainActivity
    }
}