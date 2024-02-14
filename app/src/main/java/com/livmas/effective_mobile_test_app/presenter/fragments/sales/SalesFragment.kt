package com.livmas.effective_mobile_test_app.presenter.fragments.sales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.livmas.effective_mobile_test_app.R
import com.livmas.effective_mobile_test_app.databinding.FragmentSalesBinding
import com.livmas.effective_mobile_test_app.presenter.fragments.BaseNavigationFragment

class SalesFragment : BaseNavigationFragment() {

    private val viewModel: SalesViewModel by viewModels()
    private lateinit var binding: FragmentSalesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSalesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.setTitleResource(R.string.title_sales_page)
    }
}