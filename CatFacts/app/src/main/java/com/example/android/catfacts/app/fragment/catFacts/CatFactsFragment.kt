package com.example.android.catfacts.app.fragment.catFacts

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.android.catfacts.app.adapter.CatFactsAdapter
import com.example.android.catfacts.app.dialogs.CatFactItemDialogFragment
import com.example.android.catfacts.databinding.CatFactsFragmentBinding
import com.example.android.catfacts.util.errorTimber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class CatFactsFragment : Fragment() {

    companion object {
        fun newInstance() = CatFactsFragment()
        private val DIALOG_TAG = "DIALOG_TAG"
    }

    private val viewModel by inject<CatFactsViewModel>()
    private val catFactsAdapter by inject<CatFactsAdapter>()
    private var bindingImpl: CatFactsFragmentBinding? = null
    private val binding get() = bindingImpl!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingImpl = CatFactsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCatFacts.apply {
            setHasFixedSize(true)
            adapter = catFactsAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        catFactsAdapter.getCatFactsDataLiveData().observe(viewLifecycleOwner) {position->
            try {
                val imgBitmap = viewModel.getImgBitmapList()[position]
                GlobalScope.launch {

                    val text = viewModel.getCatFactsList()!![position].text
                    showDialog(imgBitmap, text)

                }
            } catch (ex: Exception) {
                errorTimber(ex)
            }
        }

        fillAdapter()
    }

    private fun fillAdapter() {
        GlobalScope.launch {
            viewModel.fillAdapter(catFactsAdapter)
        }
    }

    private suspend fun showDialog(imgBitmap: Bitmap?, text: String) {
        withContext(Dispatchers.Main) {
            CatFactItemDialogFragment.newInstance(imgBitmap, text)
                .show(parentFragmentManager, DIALOG_TAG)
        }
    }

}