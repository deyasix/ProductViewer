package com.example.productviewer.di

import com.example.productviewer.data.ProductsDataSourceImpl
import com.example.productviewer.data.remote.ProductsService
import com.example.productviewer.domain.ProductsDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideProductsDataSource(productsService: ProductsService): ProductsDataSource =
        ProductsDataSourceImpl(productsService)

}