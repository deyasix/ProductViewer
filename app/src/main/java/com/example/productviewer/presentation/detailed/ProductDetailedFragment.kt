package com.example.productviewer.presentation.detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.productviewer.MainApplication
import com.example.productviewer.R
import com.example.productviewer.databinding.FragmentProductDetailedBinding
import com.example.productviewer.domain.entity.Product
import com.example.productviewer.presentation.base.BaseFragment

class ProductDetailedFragment : BaseFragment<FragmentProductDetailedBinding>() {

    private val args by navArgs<ProductDetailedFragmentArgs>()

    private val viewModel: ProductDetailedViewModel by lazy {
        (requireActivity().application as MainApplication).appComponent.let { appComponent ->
            val factory =
                ProductDetailedViewModel.Factory(
                    args.productId,
                    appComponent.getProductUseCase(),
                    appComponent.getFavoriteProductsRepository()
                )
            ViewModelProvider(this, factory)[ProductDetailedViewModel::class.java]
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProductDetailedBinding
        get() = FragmentProductDetailedBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.cbFavorite.setOnCheckedChangeListener { _, isChecked ->
            viewModel.switchFavorite(isChecked)
        }
    }

    private fun observeState() {
        viewModel.product.observe(viewLifecycleOwner) {
            setupProductInfo(it)
        }
        viewModel.isFavorite.observe(viewLifecycleOwner) {
            binding.cbFavorite.isChecked = it
        }
    }

    private fun setupProductInfo(product: Product) {
        with(binding) {
            Glide.with(requireContext()).load(product.imageUrl).into(ivImage)
            tvTitle.text = product.title
            tvPrice.text = getString(R.string.price, product.price.toString())
            tvCategory.text = getString(R.string.category, product.category)
            tvDescription.text = product.description
        }
    }
}