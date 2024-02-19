package com.livmas.ui.fragments.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.livmas.ui.ItemKeeper.Companion.openedItemId
import com.livmas.ui.models.ItemModel
import com.livmas.data.retrofit.models.CharacteristicModel
import com.livmas.data.retrofit.repositories.CatalogRepositoryImpl
import com.livmas.ui.HostActivity
import com.livmas.ui.ItemKeeper
import com.livmas.ui.R
import com.livmas.ui.adapters.CharacteristicsAdapter
import com.livmas.ui.fragments.SendingFragment
import com.livmas.ui.adapters.PhotoPagerAdapter
import com.livmas.ui.databinding.FragmentItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemFragment : SendingFragment() {
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

        setupObservers()
        setupListeners()

        initiateValues()
    }

    override fun onResume() {
        super.onResume()
        hideActivityTitle()
    }

    override fun onPause() {
        showActivityTitle()
        super.onPause()
    }


    private fun initiateValues() =
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.mutableModel.postValue(
                openedItemId?.let {
                    CatalogRepositoryImpl.instance.getItemById(it)?.let { response ->
                        ItemKeeper.openedItemImages?.let { images ->
                            ItemModel(
                                response,
                                images
                            )
                        }
                    }
                }
            )
        }

    private fun setupObservers() {
        setupItemObserver()
        setupDescriptionShownObserver()
        setupIngredientsShownObserver()
    }

    private fun setupItemObserver() {
        viewModel.mutableModel.observe(viewLifecycleOwner) {
            it?.apply {
                binding.apply {
                    includePager.vpPhotos.adapter = PhotoPagerAdapter(images)
                    includePager.ciIndicator.setViewPager(includePager.vpPhotos)

                    tvTitle.text = title
                    tvSubtitle.text = subtitle
                    tvAvailableCount.text =
                        resources.getString(R.string.available_pattern, available)

                    tvRating.text = rating.toString()
                    rbRating.rating = rating
                    tvReviewsCount.text = defineReviewsString(reviewsCount)

                    tvPrice.text = getPriceText(price, unit)
                    tvOldPrice.text = getPriceText(oldPrice, unit)
                    tvDiscount.text = resources.getString(R.string.discount_pattern, discount)

                    tvBrandTitle.text = title
                    tvDescription.text = description

                    initCharacteristics(info)
                    tvIngredients.text = ingredients

                    tvPriceBottom.text = getPriceText(price, unit)
                    tvOldPriceBottom.text = getPriceText(oldPrice, unit)
                }
            }
        }
    }

    private fun initCharacteristics(info: List<CharacteristicModel>) {
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.VERTICAL

        binding.rvCharacteristicsList.apply {
            layoutManager = manager
            adapter = CharacteristicsAdapter(info)
            addItemDecoration(DividerItemDecoration(
                requireContext(), manager.orientation
            ))
        }
    }

    private fun setupDescriptionShownObserver() {
        viewModel.descriptionShown.observe(viewLifecycleOwner) {
            binding.apply {
                if (it) {
                    tvDescription.visibility = View.VISIBLE
                    llProductorButton.visibility = View.VISIBLE
                }
                else {
                    tvDescription.visibility = View.GONE
                    llProductorButton.visibility = View.GONE
                }
            }
        }
    }

    private fun setupIngredientsShownObserver() {
        viewModel.ingredientsShown.observe(viewLifecycleOwner) {

            binding.tvIngredients.maxLines = if (it)
                Integer.MAX_VALUE
            else
                resources.getInteger(R.integer.ingredients_lines_limit)
        }
    }

    private fun setupListeners() {
        setupBackButtonListener()
        setupHideShowListeners()
    }

    private fun setupHideShowListeners() = binding.apply {
        tbHideDescription.setOnCheckedChangeListener { _, b ->
            viewModel.descriptionShown.postValue(b)
        }

        tbHideIngredients.setOnCheckedChangeListener { _, b ->
            viewModel.ingredientsShown.postValue(b)
        }
    }

    private fun setupBackButtonListener() {
        binding.ibBack.setOnClickListener {
            val activity = requireActivity() as HostActivity
            activity.navController.navigateUp()
        }
    }

    private fun defineReviewsString(count: Int) = when(count) {
        1 -> resources.getString(R.string.review_one_count)
        in 2..4 -> resources.getString(R.string.review_few_count_pattern, count)
        else -> resources.getString(R.string.review_lot_count_pattern, count)
    }

    private fun getPriceText(price: Int, unit: Char) =
        resources.getString(R.string.price_pattern, price, unit)
}