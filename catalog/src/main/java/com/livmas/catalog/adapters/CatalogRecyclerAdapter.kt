package com.livmas.catalog.adapters

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.livmas.catalog.R
import com.livmas.catalog.databinding.ItemLayoutBinding
import com.livmas.catalog.fragments.item.ItemKeeper
import com.livmas.catalog.models.CatalogItem
import com.livmas.utils.CATALOG_TAG

internal class CatalogRecyclerAdapter(
    private val resources: Resources,
    private val data: List<CatalogItem>,
    private val navController: NavController
): RecyclerView.Adapter<CatalogRecyclerAdapter.ItemHolder>() {

    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemLayoutBinding.bind(itemView)

        fun bind(item: CatalogItem) =
            binding.apply {
                item.apply {
                    tvCost.text = resources.getString(R.string.price_pattern, price, unit)
                    tvOldCost.text = resources.getString(R.string.price_pattern, oldPrice, unit)

                    tvItemTitle.text = item.title
                    tvItemSubtitle.text = item.subtitle

                    tvDiscount.text = resources.getString(R.string.discount_pattern, item.discount)

                    tvRating.text = item.rating.toString()
                    tvReviewsCount.text = resources.getString(R.string.brackets_pattern, item.reviewsCount)

                    bindPager(item.images)
                }
            }

        private fun bindPager(images: List<Drawable>) {
            binding.includePager.vpPhotos.adapter = PhotoPagerAdapter(images)
            binding.includePager.ciIndicator.setViewPager(binding.includePager.vpPhotos)
        }

        fun setupItemListener(item: CatalogItem) {
            binding.root.setOnClickListener {
                Log.d(CATALOG_TAG, "Item clicked")

                ItemKeeper.openedItemImages = item.images
                ItemKeeper.openedItemId = item.id
                navController.navigate(R.id.action_catalog_navigation_main_to_catalog_navigation_item)
            }
        }

        fun setupLikeButton() {
            binding.ibLike.setOnClickListener {
                Log.i(CATALOG_TAG, "Item liked")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.apply {
            bind(data[position])

            setupItemListener(data[position])
            setupLikeButton()
        }
    }

    override fun getItemCount(): Int = data.size
}