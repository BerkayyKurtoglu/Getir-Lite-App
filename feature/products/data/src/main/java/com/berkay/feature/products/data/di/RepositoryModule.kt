package com.berkay.feature.products.data.di

import com.berkay.feature.products.data.ProductRepositoryImpl
import com.berkay.feature.products.domain.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductsRepository

}
