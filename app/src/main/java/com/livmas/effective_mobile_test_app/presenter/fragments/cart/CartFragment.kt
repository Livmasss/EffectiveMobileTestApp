package com.livmas.effective_mobile_test_app.presenter.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.livmas.effective_mobile_test_app.databinding.FragmentCartBinding
import com.livmas.ui.fragments.SendingFragment

class CartFragment : SendingFragment() {

    private val viewModel: CartViewModel by viewModels()
    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = resources.getString(com.livmas.ui.R.string.title_cart_page)
    }
}