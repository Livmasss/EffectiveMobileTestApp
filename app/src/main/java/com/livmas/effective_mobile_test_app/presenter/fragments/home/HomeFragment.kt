package com.livmas.effective_mobile_test_app.presenter.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.livmas.effective_mobile_test_app.R
import com.livmas.effective_mobile_test_app.databinding.FragmentHomeBinding
import com.livmas.effective_mobile_test_app.presenter.fragments.BaseNavigationFragment

class HomeFragment : BaseNavigationFragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.setTitleResource(R.string.title_home_page)
    }
}