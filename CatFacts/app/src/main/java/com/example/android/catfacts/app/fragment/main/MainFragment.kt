package com.example.android.catfacts.app.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.catfacts.R
import com.example.android.catfacts.databinding.MainFragmentBinding
import com.example.android.catfacts.util.navigateTo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel by inject<MainViewModel>()
    private var bindingImpl: MainFragmentBinding? = null
    private val binding get() = bindingImpl!!


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

    }

    private fun setOnClickCatFacts() {
        binding.catFactsButton
            .setOnClickListener { view: View ->
                GlobalScope.launch {
                    loadCatFacts()
                    navigateToImpl(view, R.id.catFactsFragment)
                }
            }
    }

    private fun navigateToImpl(view: View, viewId: Int) {
        navigateTo(view, viewId)
    }

    private suspend fun loadCatFacts() {
        viewModel.loadCatFacts()
    }


}