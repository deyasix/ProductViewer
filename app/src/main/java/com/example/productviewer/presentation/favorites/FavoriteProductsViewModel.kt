package com.example.productviewer.presentation.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.productviewer.domain.GetFavoriteProductsUseCase
import com.example.productviewer.domain.entity.Product
import javax.inject.Inject

class FavoriteProductsViewModel @Inject constructor(getFavoriteProductsUseCase: GetFavoriteProductsUseCase) :
    ViewModel() {

    val favoriteProducts: LiveData<List<Product>> = getFavoriteProductsUseCase().asLiveData()

}