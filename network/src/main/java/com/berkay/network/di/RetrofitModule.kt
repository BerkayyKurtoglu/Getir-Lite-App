package com.berkay.network.di

import com.berkay.contract.AppBuildConfig
import com.berkay.contract.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @BaseUrl
    @Provides
    @Singleton
    fun provideBaseUrl(
        appBuildConfig: AppBuildConfig
    ) : String =
        appBuildConfig.baseUrl

    @Provides
    @Singleton
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: Converter.Factory
    ) : Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()

}
