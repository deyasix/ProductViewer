package com.example.productviewer.di

import android.content.Context
import com.example.productviewer.domain.GetProductUseCase
import com.example.productviewer.presentation.detailed.ProductDetailedFragment
import com.example.productviewer.presentation.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataSourceModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun inject(homeFragment: HomeFragment)
    fun inject(productDetailedFragment: ProductDetailedFragment)

    fun getProductUseCase(): GetProductUseCase

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ApplicationComponent
    }
}