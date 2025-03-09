package com.example.productviewer.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productviewer.domain.GetProductsUseCase
import com.example.productviewer.domain.entity.Product
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getProductsUseCase: GetProductsUseCase) :
    ViewModel() {

    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
    val products: LiveData<List<Product>> get() = _products

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            _products.postValue(getProductsUseCase.invoke())
        }
    }

}