package com.livmas.catalog.fragments.catalog

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.livmas.catalog.R
import com.livmas.catalog.adapters.CatalogRecyclerAdapter
import com.livmas.catalog.databinding.FragmentCatalogBinding
import com.livmas.ui.SendingFragment


class CatalogFragment : SendingFragment() {

    private val viewModel: CatalogViewModel by viewModels()
    private lateinit var binding: FragmentCatalogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatalogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = resources.getString(R.string.title_catalog_page)

        requireActivity().runOnUiThread {

            setupSpinner()
            setupTagChips()
            setupAdapterData()
            setupCatalogRecycler()
        }
    }

    private fun setupTagChips() {
        binding.cgTags.setOnCheckedStateChangeListener { group, checkedIds ->
            for (chip in group.children) {
                chip as Chip
                chip.isCloseIconVisible = false
            }

            if (checkedIds.size == 0) {
                group.check(group[0].id)
                return@setOnCheckedStateChangeListener
            }

            group.findViewById<Chip>(checkedIds[0]).isCloseIconVisible = true
        }
    }

    private fun setupSpinner() {
        val spinner: Spinner = binding.spinner

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            R.layout.icon_sorting_spinner_layout, R.id.tvSorting,
            resources.getStringArray(R.array.sorting_types)
        )
        adapter.setDropDownViewResource(R.layout.sorting_spinner_layout)

        spinner.adapter = adapter
    }

    private fun setupCatalogRecycler() {
        binding.rvCatalog.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.catalogContent.observe(viewLifecycleOwner) {
            binding.rvCatalog.adapter = CatalogRecyclerAdapter(
                resources, it )
        }
    }

    private fun setupAdapterData() {
        val images = ArrayList<Drawable>()

        images.apply {
            addDrawable(getDrawableById(R.drawable.catalog_image_0))
            addDrawable(getDrawableById(R.drawable.catalog_image_1))
            addDrawable(getDrawableById(R.drawable.catalog_image_2))
            addDrawable(getDrawableById(R.drawable.catalog_image_3))
            addDrawable(getDrawableById(R.drawable.catalog_image_4))
            addDrawable(getDrawableById(R.drawable.catalog_image_5))
        }

        viewModel.fillAdapterWithData(images)
    }

    private fun getDrawableById(resId: Int) =
        ResourcesCompat.getDrawable(resources, resId, requireActivity().theme)
    private fun ArrayList<Drawable>.addDrawable(drawable: Drawable?) = drawable?.let { add(it) }
}