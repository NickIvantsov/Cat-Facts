package com.example.android.catfacts.app.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.android.catfacts.R
import com.example.android.catfacts.app.fragment.main.MainViewModel
import com.example.android.catfacts.app.model.CatFactItem
import com.example.android.catfacts.app.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatFactsAdapter(private val repository: IRepository) :
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
                    val bitmap:Bitmap = MainViewModel.bitmapList[position]!!
                    val width = bitmap.width
                    val height = bitmap.height
                    //половиним
                    val halfWidth = width/2
                    val halfHeight = height/2
                    val bmHalf = Bitmap.createScaledBitmap(bitmap,halfWidth,halfHeight,false)
                    withContext(Dispatchers.Main) {
                            ivCatImg.setImageBitmap(bmHalf)
                    }
                }
                cardViewMainInfo.setOnClickListener {
                    catFactsDataLiveData.value = position
                }
            }
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

    fun removeAll() {
        data.clear()
    }
    fun getCatFactsDataLiveData(): LiveData<Int> {
        return catFactsDataLiveData
    }
}
