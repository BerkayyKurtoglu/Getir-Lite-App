package com.berkay.feature.common.data

import com.berkay.feature.common.domain.CardCacheManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheManagerModule {

    @Binds
    abstract fun bindCacheManager(impl: CardCacheManagerImpl): CardCacheManager

}