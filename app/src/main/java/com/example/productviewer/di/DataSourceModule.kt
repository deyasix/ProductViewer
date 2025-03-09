package com.example.productviewer.di

import com.example.productviewer.data.local.db.FavoriteProductsDataSourceImpl
import com.example.productviewer.data.local.db.dao.FavoriteProductsDao
import com.example.productviewer.data.remote.ProductsDataSourceImpl
import com.example.productviewer.data.remote.ProductsService
import com.example.productviewer.domain.FavoriteProductsDataSource
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

    @Provides
    @Singleton
    fun provideFavoriteProductsDataSource(favoriteProductsDao: FavoriteProductsDao): FavoriteProductsDataSource =
        FavoriteProductsDataSourceImpl(favoriteProductsDao)

}