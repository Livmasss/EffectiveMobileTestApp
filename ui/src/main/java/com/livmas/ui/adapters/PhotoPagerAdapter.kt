package com.livmas.ui.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.livmas.ui.R
import com.livmas.ui.databinding.ItemPhotoLayoutBinding

class PhotoPagerAdapter(private val photosRes: List<Drawable>): RecyclerView.Adapter<PhotoPagerAdapter.PhotoViewHolder>() {
    inner class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = ItemPhotoLayoutBinding.bind(itemView)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoPagerAdapter.PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_photo_layout, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoPagerAdapter.PhotoViewHolder, position: Int) {
        holder.binding.ivPhoto.setImageDrawable(photosRes[position])
    }

    override fun getItemCount(): Int = photosRes.size
}