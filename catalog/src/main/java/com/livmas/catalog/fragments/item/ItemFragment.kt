package com.livmas.catalog.fragments.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.livmas.catalog.R
import com.livmas.catalog.adapters.PhotoPagerAdapter
import com.livmas.catalog.databinding.FragmentItemBinding

class ItemFragment : Fragment() {
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
        initiateValues()
    }

    private fun initiateValues() =
        ItemKeeper.openedItem?.apply{
            binding.apply {
                includePager.vpPhotos.adapter = PhotoPagerAdapter(images)
                includePager.ciIndicator.setViewPager(includePager.vpPhotos)

                tvTitle.text = title
                tvSubtitle.text = subtitle
                tvAvailableCount.text = resources.getString(R.string.available_pattern, available)

                tvRating.text = rating.toString()
                rbRating.rating = rating
                tvAvailableCount.text = defineReviewsString(available)

                tvPrice.text = getPriceText(price, unit)
                tvOldPrice.text = getPriceText(oldPrice, unit)
                tvDiscount.text = resources.getString(R.string.discount_pattern, discount)

                tvBrandTitle.text = title
                tvDescription.text = description

                tvIngredients.text = ingredients

                tvPriceBottom.text = getPriceText(price, unit)
                tvOldPriceBottom.text = getPriceText(oldPrice, unit)
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