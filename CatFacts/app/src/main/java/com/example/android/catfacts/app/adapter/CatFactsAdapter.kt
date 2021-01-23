package com.example.android.catfacts.app.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.catfacts.R
import com.example.android.catfacts.app.fragment.main.MainViewModel
import com.example.android.catfacts.app.model.CatFactItem
import com.example.android.catfacts.app.repository.IRepository
import com.example.android.catfacts.databinding.CatFactItemViewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatFactsAdapter(private val repository: IRepository,private val context:Context) :
    RecyclerView.Adapter<CatFactsViewHolder>() {

    private val catFactsDataLiveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    private var data = ArrayList<CatFactItem>()
    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: CatFactsViewHolder, position: Int) {
        val item = data[position]
        holder.binding.apply {
            item.apply {
                tvFact.text = item.text
                GlobalScope.launch {
                    val bitmap:Bitmap = repository.getImgBitmapList()[position]!!
                    setImg(bitmap)
                }
                setOnClickListener(position)
            }
        }

    }

    private suspend fun CatFactItemViewBinding.setImg(bitmap: Bitmap) {
        withContext(Dispatchers.Main) {
            Glide
                .with(context)
                .load(bitmap)
                .into(ivCatImg)
//            ivCatImg.setImageBitmap(bitmap)
        }
    }

    private fun CatFactItemViewBinding.setOnClickListener(
        position: Int
    ) {
        cardViewMainInfo.setOnClickListener {
            catFactsDataLiveData.value = position
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): CatFactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View =
            inflater.inflate(R.layout.cat_fact_item_view, parent, false)
        return CatFactsViewHolder(view)
    }

    fun add(catFacts: CatFactItem) {
        data.forEach {
            when (it) {
                catFacts -> {
                    return
                }
            }
        }
        data.add(catFacts)
        notifyItemInserted(data.size - 1)
    }

    fun getCatFactsDataLiveData(): LiveData<Int> {
        return catFactsDataLiveData
    }
}
