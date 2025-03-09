package com.example.productviewer.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.productviewer.MainApplication
import com.example.productviewer.databinding.FragmentHomeBinding
import com.example.productviewer.di.GenericViewModelFactory
import com.example.productviewer.presentation.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    @Inject
    lateinit var factory: GenericViewModelFactory<HomeViewModel>

    private val viewModel: HomeViewModel by viewModels { factory }
    private val adapter: ProductsAdapter = ProductsAdapter()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as? MainApplication)?.appComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
        setupViewPager()
    }

    private fun setupViewPager() {
        with(binding) {
            viewPager.adapter = adapter
            TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()
        }
    }

    private fun observeState() {
        viewModel.products.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}