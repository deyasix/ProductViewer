package com.example.productviewer.di

import com.example.productviewer.BuildConfig
import com.example.productviewer.data.remote.ProductsService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideProductsService(retrofit: Retrofit): ProductsService {
        return retrofit.create(ProductsService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptors: ArrayList<Interceptor>
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .followRedirects(false)
        interceptors.forEach {
            clientBuilder.addInterceptor(it)
        }
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideInterceptors(): ArrayList<Interceptor> {
        val interceptors = arrayListOf<Interceptor>()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        interceptors.add(loggingInterceptor)
        return interceptors
    }

}