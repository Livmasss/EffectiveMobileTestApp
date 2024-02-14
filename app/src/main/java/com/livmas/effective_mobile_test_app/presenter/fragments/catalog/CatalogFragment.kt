package com.livmas.effective_mobile_test_app.presenter.fragments.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.viewModels
import com.livmas.effective_mobile_test_app.R
import com.livmas.effective_mobile_test_app.databinding.FragmentCatalogBinding
import com.livmas.effective_mobile_test_app.presenter.fragments.BaseNavigationFragment


class CatalogFragment : BaseNavigationFragment() {

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
        mainActivity.setTitleResource(R.string.title_catalog_page)

        val spinner: Spinner = binding.spinner

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            R.layout.icon_sorting_spinner_layout, R.id.tvSorting,
            resources.getStringArray(R.array.sorting_types)
        )
        adapter.setDropDownViewResource(R.layout.sorting_spinner_layout)

        spinner.adapter = adapter
    }
}