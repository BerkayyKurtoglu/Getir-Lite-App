package com.berkay.getirlite.di

import com.berkay.contract.AppBuildConfig
import com.berkay.getirlite.appconfig.AppBuildConfigImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConfigModule {

    @Binds
    @Singleton
    abstract fun bindConfig(config: AppBuildConfigImpl): AppBuildConfig

}
