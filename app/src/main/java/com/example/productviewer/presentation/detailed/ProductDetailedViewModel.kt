package com.example.productviewer.presentation.detailed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.productviewer.domain.FavoriteProductsRepository
import com.example.productviewer.domain.GetProductUseCase
import com.example.productviewer.domain.entity.Product
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailedViewModel @Inject constructor(
    private val productId: Long,
    private val getProductUseCase: GetProductUseCase,
    private val favoriteProductsRepository: FavoriteProductsRepository
) : ViewModel() {

    private val _product: MutableLiveData<Product> = MutableLiveData()
    val product: LiveData<Product> get() = _product

    val isFavorite: LiveData<Boolean> =
        favoriteProductsRepository.isFavoriteProduct(productId).asLiveData()

    init {
        getProduct()
    }

    private fun getProduct() {
        viewModelScope.launch {
            _product.postValue(getProductUseCase.invoke(productId))
        }
    }

    fun switchFavorite(isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                product.value?.let { product ->
                    favoriteProductsRepository.addFavoriteProduct(product)
                }
            } else favoriteProductsRepository.deleteFavoriteProduct(productId)
        }
    }

    class Factory(
        private val productId: Long,
        private val getProductUseCase: GetProductUseCase,
        private val favoriteProductsRepository: FavoriteProductsRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProductDetailedViewModel(
                productId, getProductUseCase, favoriteProductsRepository
            ) as T
        }
    }
}