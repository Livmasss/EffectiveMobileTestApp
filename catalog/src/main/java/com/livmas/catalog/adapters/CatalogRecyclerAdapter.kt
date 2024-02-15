package com.livmas.catalog.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.livmas.catalog.R
import com.livmas.catalog.databinding.CatalogItemLayoutBinding
import com.livmas.catalog.models.CatalogItem

class CatalogRecyclerAdapter(private val resources: Resources, private val data: List<CatalogItem>): RecyclerView.Adapter<CatalogRecyclerAdapter.ItemHolder>() {
    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CatalogItemLayoutBinding.bind(itemView)

        fun bind(item: CatalogItem) =
            binding.apply {
                tvCost.text = item.price
                tvOldCost.text = item.oldPrice

                tvItemTitle.text = item.title
                tvItemSubtitle.text = item.subtitle

                tvDiscount.text = resources.getString(R.string.discount_pattern, item.discount)

                tvRating.text = item.rating.toString()
                tvReviewsCount.text = resources.getString(R.string.brackets_pattern, item.reviewsCount)

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.catalog_item_layout, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}