package com.livmas.catalog.fragments.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.core.view.children
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.livmas.catalog.R
import com.livmas.catalog.adapters.CatalogRecyclerAdapter
import com.livmas.catalog.databinding.FragmentCatalogBinding
import com.livmas.catalog.models.SortingMode
import com.livmas.ui.fragments.SendingFragment
import com.livmas.ui.getItemsImages
import com.livmas.ui.models.enums.ItemTag

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

            setupSortingSpinner()
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

            val checkedChip = group.findViewById<Chip>(checkedIds[0])
            checkedChip.isCloseIconVisible = true

            val index = group.indexOfChild(checkedChip) - 1
            if (index == -1)
                viewModel.setTag(null)
            else
                viewModel.setTag(ItemTag.values()[index])
        }
    }

    private fun setupSortingSpinner() {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            R.layout.icon_sorting_spinner_layout, R.id.tvSorting,
            resources.getStringArray(R.array.sorting_types)
        )

        adapter.setDropDownViewResource(R.layout.sorting_spinner_layout)
        binding.sSorting.adapter = adapter
        binding.sSorting.setSelection(viewModel.sortingMode.ordinal)

        setupSpinnerListener()
    }

    private fun setupCatalogRecycler() {
        binding.rvCatalog.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.catalogContent.observe(viewLifecycleOwner) {
            if (it == null)
                return@observe

            binding.rvCatalog.adapter = navController?.let { controller ->
                CatalogRecyclerAdapter(
                    resources, it, controller
                )
            }
            binding.rvCatalog.adapter?.itemCount?.let { count ->
                binding.rvCatalog.adapter?.notifyItemRangeInserted(0,
                    count
                )
            }

            binding.pbLoading.visibility = View.GONE
        }
    }

    private fun setupAdapterData() {
        viewModel.fillAdapterWithData(getItemsImages(resources, requireActivity().theme))
        startProgressBar()
    }

    private fun startProgressBar() {
        binding.pbLoading.visibility = View.VISIBLE
    }
    private fun setupSpinnerListener() {
        binding.apply {
            sSorting.onItemSelectedListener = object: OnItemSelectedListener {
                override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    SortingMode.values()[position].also {
                        viewModel.setSortMode(it)
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    sSorting.setSelection(0)
                }
            }
        }
    }
}