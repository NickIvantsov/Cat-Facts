package com.example.android.catfacts.app.fragment.catFacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.catfacts.R
import com.example.android.catfacts.app.adapter.CatFactsAdapter
import com.example.android.catfacts.app.dialogs.CatFactItemDialogFragment
import com.example.android.catfacts.app.fragment.main.MainFragment.Companion.data
import com.example.android.catfacts.databinding.CatFactsFragmentBinding
import org.koin.android.ext.android.inject

class CatFactsFragment : Fragment() {

    companion object {
        fun newInstance() = CatFactsFragment()
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
        data!!.forEach{
            catFactsAdapter.add(it)
            catFactsAdapter.notifyDataSetChanged()
        }
        catFactsAdapter.getCatFactsDataLiveData().observe(viewLifecycleOwner){
            CatFactItemDialogFragment.newInstance(it.url,it.text).show(parentFragmentManager,"Some_Tag")
        }
    }

}