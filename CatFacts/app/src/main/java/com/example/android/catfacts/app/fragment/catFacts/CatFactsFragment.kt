package com.example.android.catfacts.app.fragment.catFacts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.catfacts.R

class CatFactsFragment : Fragment() {

    companion object {
        fun newInstance() = CatFactsFragment()
    }

    private lateinit var viewModel: CatFactsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cat_facts_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CatFactsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}