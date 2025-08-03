package com.berkay.getirlite.appconfig

import com.berkay.contract.AppBuildConfig
import com.berkay.getirlite.BuildConfig
import javax.inject.Inject

class AppBuildConfigImpl @Inject constructor() : AppBuildConfig {
    override val baseUrl: String
        get() = BuildConfig.BASE_URL
}