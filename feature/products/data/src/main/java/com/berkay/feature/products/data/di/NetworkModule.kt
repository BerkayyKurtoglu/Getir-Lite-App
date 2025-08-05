package com.berkay.feature.products.data.di

import com.berkay.feature.products.data.remote.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    @Provides
    fun provideProductService(retrofit: Retrofit): ProductService {
        return retrofit.create()
    }

}
