package com.example.productviewer.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.productviewer.MainApplication
import com.example.productviewer.databinding.FragmentFavoriteProductsBinding
import com.example.productviewer.di.GenericViewModelFactory
import com.example.productviewer.presentation.base.BaseFragment
import javax.inject.Inject

class FavoriteProductsFragment : BaseFragment<FragmentFavoriteProductsBinding>() {

    @Inject
    lateinit var factory: GenericViewModelFactory<FavoriteProductsViewModel>

    private val viewModel: FavoriteProductsViewModel by viewModels { factory }
    private val adapter: FavoriteProductAdapter =
        FavoriteProductAdapter(::navigateToDetailedProductFragment)

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteProductsBinding
        get() = FragmentFavoriteProductsBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as? MainApplication)?.appComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
        setupRecyclerView()
    }

    private fun observeState() {
        viewModel.favoriteProducts.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            if (it.isEmpty()) binding.tvEmpty.visibility = View.VISIBLE
            else binding.tvEmpty.visibility = View.GONE
        }
    }

    private fun setupRecyclerView() {
        binding.rvFavorites.adapter = adapter
    }

    private fun navigateToDetailedProductFragment(productId: Long) {
        findNavController().navigate(
            FavoriteProductsFragmentDirections.navigateToProductDetailedFragment(productId)
        )
    }
}