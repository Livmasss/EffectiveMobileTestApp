package com.livmas.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.livmas.data.retrofit.models.CharacteristicModel
import com.livmas.ui.R
import com.livmas.ui.databinding.ItemCharacteristicLayoutBinding

internal class CharacteristicsAdapter(private val data: List<CharacteristicModel>):
    RecyclerView.Adapter<CharacteristicsAdapter.CharacteristicViewHolder>() {

    inner class CharacteristicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCharacteristicLayoutBinding.bind(itemView)

        fun bind(characteristic: CharacteristicModel) {
            binding.tvCTitle.text = characteristic.title
            binding.tvCValue.text = characteristic.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacteristicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_characteristic_layout, parent, false)
        return CharacteristicViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacteristicViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}