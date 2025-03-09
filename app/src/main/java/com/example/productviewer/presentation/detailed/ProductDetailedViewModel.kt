package com.example.productviewer.presentation.detailed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.productviewer.domain.GetProductUseCase
import com.example.productviewer.domain.entity.Product
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailedViewModel @Inject constructor(
    private val productId: Long,
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {

    private val _product: MutableLiveData<Product> = MutableLiveData()
    val product: LiveData<Product> get() = _product

    init {
        getProduct()
    }

    private fun getProduct() {
        viewModelScope.launch {
            _product.postValue(getProductUseCase.invoke(productId))
        }
    }

    class Factory(
        private val productId: Long,
        private val getProductUseCase: GetProductUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProductDetailedViewModel(
                productId, getProductUseCase
            ) as T
        }
    }
}