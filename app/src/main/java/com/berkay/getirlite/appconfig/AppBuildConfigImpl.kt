package com.berkay.getirlite.appconfig

import com.berkay.contract.AppBuildConfig
import com.berkay.getirlite.BuildConfig

class AppBuildConfigImpl : AppBuildConfig {
    override val baseUrl: String
        get() = BuildConfig.BASE_URL
}