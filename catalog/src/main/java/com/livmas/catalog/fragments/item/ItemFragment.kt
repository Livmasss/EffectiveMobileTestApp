package com.livmas.catalog.fragments.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.livmas.catalog.R
import com.livmas.catalog.adapters.PhotoPagerAdapter
import com.livmas.catalog.databinding.FragmentItemBinding
import com.livmas.catalog.fragments.item.ItemKeeper.Companion.openedItemId
import com.livmas.catalog.models.ItemModel
import com.livmas.data.repositories.CatalogRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        setupItemObserver()
        setupDescriptionShownObserver()
        setupIngredientsShownObserver()

        initiateValues()
    }

    private fun initiateValues() =
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.mutableModel.postValue(
                openedItemId?.let {
                    CatalogRepository.instance.getItemById(it)?.let { response ->
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
                    tvReviewsCount.text = defineReviewsString(available)

                    tvPrice.text = getPriceText(price, unit)
                    tvOldPrice.text = getPriceText(oldPrice, unit)
                    tvDiscount.text = resources.getString(R.string.discount_pattern, discount)

                    tvBrandTitle.text = title
                    tvDescription.text = description

                    tvIngredients.text = ingredients

                    tvPriceBottom.text = getPriceText(price, unit)
                    tvOldPriceBottom.text = getPriceText(oldPrice, unit)

                    tbHideDescription.setOnCheckedChangeListener { _, b ->
                        viewModel.descriptionShown.postValue(b)
                    }

                    tbHideIngredients.setOnCheckedChangeListener { _, b ->
                        viewModel.ingredientsShown.postValue(b)
                    }
                }
            }
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

    private fun defineReviewsString(count: Int) = when(count) {
        1 -> resources.getString(R.string.review_one_count)
        in 2..4 -> resources.getString(R.string.review_few_count_pattern, count)
        else -> resources.getString(R.string.review_lot_count_pattern, count)
    }

    private fun getPriceText(price: Int, unit: Char) =
        resources.getString(R.string.price_pattern, price, unit)
}