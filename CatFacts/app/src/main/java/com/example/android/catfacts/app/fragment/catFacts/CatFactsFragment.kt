package com.example.android.catfacts.app.fragment.catFacts

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.catfacts.app.adapter.CatFactsAdapter
import com.example.android.catfacts.app.dialogs.CatFactItemDialogFragment
import com.example.android.catfacts.app.fragment.main.MainViewModel
import com.example.android.catfacts.app.repository.RepositoryImpl
import com.example.android.catfacts.databinding.CatFactsFragmentBinding
import com.example.android.catfacts.util.errorTimber
import org.koin.android.ext.android.inject

class CatFactsFragment : Fragment() {

    companion object {
        fun newInstance() = CatFactsFragment()
        private val DIALOG_TAG = "DIALOG_TAG"
    }

    private val viewModel by inject<CatFactsViewModel>()
    private val catFactsAdapter by inject<CatFactsAdapter>()
    private val repos by inject<RepositoryImpl>()
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
        catFactsAdapter.getCatFactsDataLiveData().observe(viewLifecycleOwner) {
            try {
                val imgBitmap = MainViewModel.bitmapList[it]
                val text = repos.getCatFactsData()!![it].text
                showDialog(imgBitmap, text)
            } catch (ex: Exception) {
                errorTimber(ex)
            }

        }
        viewModel.fillAdapter(catFactsAdapter)
    }

    private fun showDialog(imgBitmap: Bitmap?, text: String) {
        CatFactItemDialogFragment.newInstance(imgBitmap, text)
            .show(parentFragmentManager, DIALOG_TAG)
    }

}