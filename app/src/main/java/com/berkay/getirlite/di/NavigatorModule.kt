package com.berkay.getirlite.di

import com.berkay.common.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.berkay.getirlite.navigation.DefaultNavigation as DefaultNavigation1

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigatorModule {

    @Binds
    @Singleton
    abstract fun navigator(navigator: DefaultNavigation1): Navigator

}