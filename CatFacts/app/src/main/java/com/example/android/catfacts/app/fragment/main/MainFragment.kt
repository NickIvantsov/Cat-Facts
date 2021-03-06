package com.example.android.catfacts.app.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.android.catfacts.R
import com.example.android.catfacts.databinding.MainFragmentBinding
import com.example.android.catfacts.util.navigateTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        private const val ENABLE = "ENABLE"
        private const val DISABLE = "DISABLE"
    }

    private val viewModel by inject<MainViewModel>()
    private var bindingImpl: MainFragmentBinding? = null
    private val binding get() = bindingImpl!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingImpl = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setOnClickCatFacts()
        setActiveCatFactsBtn()
    }

    private fun setOnClickCatFacts() {
        binding.catFactsButton
            .setOnClickListener {
                catFactsButtonOnClickImpl()
            }
    }

    private fun catFactsButtonOnClickImpl() {
        setActiveBtn(DISABLE)
        GlobalScope.launch((Dispatchers.IO)) {
            if (loadCatFacts()) {
                navigateToImpl(R.id.catFactsFragment)
            } else {
                showInternetError()
            }
            setActiveBtn(ENABLE)
        }
    }

    private suspend fun showInternetError() {
        withContext(Dispatchers.Main) {
            Toast.makeText(context, getString(R.string.internet_error), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setActiveBtn(status: String) {
        when (status) {
            ENABLE -> {
                viewModel.enableBtn()
            }
            DISABLE -> {
                viewModel.disableBtn()
            }
        }
        setActiveCatFactsBtn()
    }


    private fun setActiveCatFactsBtn() {
        binding.catFactsButton.alpha = viewModel.alpha
        binding.catFactsButton.isClickable = viewModel.isClickable
    }

    private suspend fun navigateToImpl(viewId: Int) {
        withContext(Dispatchers.Main) {
            navigateTo(requireView(), viewId)
        }
    }

    private suspend fun loadCatFacts(): Boolean {
        return viewModel.loadCatFacts()
    }


}