package com.livmas.profile.fragments.liked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.livmas.profile.adapters.LikedRecyclerAdapter
import com.livmas.profile.databinding.FragmentLikedBinding
import com.livmas.ui.HostActivity
import com.livmas.ui.fragments.SendingFragment
import com.livmas.ui.getItemsImages

internal class LikedFragment : SendingFragment() {
    private val viewModel: LikedViewModel by viewModels()
    private lateinit var binding: FragmentLikedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLikedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLikedListObserver()
        setupListeners()
        setupAdapterData()
    }

    override fun onResume() {
        super.onResume()
        hideActivityTitle()
    }

    override fun onPause() {
        showActivityTitle()
        super.onPause()
    }

    private fun setupListeners() {
        setupBackButtonListener()
    }

    private fun setupLikedListObserver() {
        viewModel.likedList.observe(viewLifecycleOwner) {
            binding.rvRecycler.adapter = navController?.let { navController ->
                LikedRecyclerAdapter(
                    resources, it, navController
                )
            }
            binding.rvRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupBackButtonListener() {
        binding.ibBack.setOnClickListener {
            val activity = requireActivity() as HostActivity
            activity.navController.navigateUp()
        }
    }

    private fun setupAdapterData() {
        viewModel.fillAdapterWithData(getItemsImages(resources, requireActivity().theme))
    }
}