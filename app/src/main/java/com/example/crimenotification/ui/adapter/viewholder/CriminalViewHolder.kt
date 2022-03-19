package com.example.crimenotification.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crimenotification.R
import com.example.crimenotification.data.model.DistanceCriminal
import com.example.crimenotification.databinding.ItemCriminalListBinding

class CriminalViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_criminal_list, parent, false)
) {
    private val binding = ItemCriminalListBinding.bind(itemView)

    fun bind(
        item: DistanceCriminal
    ) {
        binding.item = item
    }
}