package com.example.android.catfacts.app.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.android.catfacts.R
import com.example.android.catfacts.app.dialogs.CatFactItemDialogFragment
import com.example.android.catfacts.app.model.CatFactItem
import com.example.android.catfacts.app.repository.IRepository
import com.example.android.catfacts.util.errorTimber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatFactsAdapter(private val repository: IRepository) :
    RecyclerView.Adapter<CatFactsViewHolder>() {

    private val catFactsDataLiveData: MutableLiveData<CatFactsData> by lazy {
        MutableLiveData<CatFactsData>()
    }

    private var data = ArrayList<CatFactItem>()
    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: CatFactsViewHolder, position: Int) {
        val item = data[position]
        holder.binding.apply {
            item.apply {
                tvFact.text = item.text
                GlobalScope.launch {
                    val catUrl = repository.getCatImgUrl()
                    withContext(Dispatchers.Main) {
                        val options = RequestOptions() //todo дублирование
                            .placeholder(R.drawable.hu_prog)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .priority(Priority.HIGH)
                            .centerCrop()
                            .dontAnimate()
                            .dontTransform()

                        withContext(Dispatchers.Main){
                            Glide.with(holder.itemView)
                                .load(catUrl!!.file)
                                .apply(options)
                                .listener(object : RequestListener<Drawable> {
                                    override fun onLoadFailed(
                                        e: GlideException?,
                                        model: Any?,
                                        target: Target<Drawable>?,
                                        isFirstResource: Boolean
                                    ): Boolean {
                                        return false
                                    }

                                    override fun onResourceReady(
                                        resource: Drawable?,
                                        model: Any?,
                                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                                        dataSource: DataSource?,
                                        isFirstResource: Boolean
                                    ): Boolean {
                                        return false
                                    }
                                })
                                .into(ivCatImg)
                        }
                        cardViewMainInfo.setOnClickListener {
                            catFactsDataLiveData.value = CatFactsData(item.text,catUrl!!.file)
                        }
                    }
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
    fun getCatFactsDataLiveData(): LiveData<CatFactsData> {
        return catFactsDataLiveData
    }
}


data class CatFactsData(val text:String,val url:String)