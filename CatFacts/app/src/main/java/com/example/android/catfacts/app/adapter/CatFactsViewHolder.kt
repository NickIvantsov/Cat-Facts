package com.example.android.catfacts.app.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.android.catfacts.databinding.CatFactItemViewBinding

class CatFactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: CatFactItemViewBinding = CatFactItemViewBinding.bind(itemView)
}