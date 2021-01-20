package com.example.android.catfacts.app.dialogs

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.android.catfacts.R
import com.example.android.catfacts.databinding.CatFactItemDialogFragmentBinding

class CatFactItemDialogFragment : DialogFragment() {

    companion object {
        fun newInstance(imgUrl: String, text: String): CatFactItemDialogFragment {
            val fragment = CatFactItemDialogFragment()
            val args = Bundle()
            args.putString(KEY_IMG_URL, imgUrl)
            args.putString(KEY_TEXT, text)

            fragment.arguments = args
            return fragment

        }

        private const val KEY_IMG_URL = "KEY_IMG_URL"
        private const val KEY_TEXT = "KEY_TEXT"

    }


    private var bindingImpl: CatFactItemDialogFragmentBinding? = null
    private val binding get() = bindingImpl!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindingImpl = CatFactItemDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    private fun setupView(view: View) {
        binding.tvFact.text = arguments?.getString(KEY_TEXT)
        val options = RequestOptions() //todo дублирование
            .placeholder(R.drawable.hu_prog)
            .centerCrop()
            .priority(Priority.HIGH)

        Glide.with(view)
            .load(arguments?.getString(KEY_IMG_URL))
            .placeholder(R.drawable.ic_launcher_background)
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
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .into(binding.ivCatImgCatItemDialog)

    }
}