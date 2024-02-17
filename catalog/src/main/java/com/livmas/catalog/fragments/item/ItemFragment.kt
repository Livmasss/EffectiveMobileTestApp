package com.livmas.catalog.fragments.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.livmas.catalog.adapters.PhotoPagerAdapter
import com.livmas.catalog.databinding.FragmentItemBinding

class ItemFragment : Fragment() {
    private val viewModel: ItemViewModel by viewModels()
    private lateinit var binding: FragmentItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.itemData?.let {
            binding.apply {
                includePager.vpPhotos.adapter =  PhotoPagerAdapter(it.images)
                includePager.ciIndicator.setViewPager(includePager.vpPhotos)
            }
        }
    }
}