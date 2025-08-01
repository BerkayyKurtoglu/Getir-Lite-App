package com.berkay.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object GsonModule {

    @Provides
    fun provideGson() : Gson =
        GsonBuilder()
            .setPrettyPrinting()
            .create()

    @Provides
    fun provideGsonConvertor(
        gson: Gson
    ) : Converter.Factory = GsonConverterFactory.create(gson)

}
