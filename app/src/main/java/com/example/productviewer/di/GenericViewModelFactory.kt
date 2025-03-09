package com.example.productviewer.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jakarta.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class GenericViewModelFactory<T : ViewModel> @Inject constructor(
    private val provider: Provider<T>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return provider.get() as T
    }
}