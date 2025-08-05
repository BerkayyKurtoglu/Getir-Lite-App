package com.berkay.build_logic.convention.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.berkay.build_logic.convention.AppConfig
import com.berkay.build_logic.convention.PluginConstants
import com.berkay.build_logic.convention.extension.addDebugImplementation
import com.berkay.build_logic.convention.extension.addImplementation
import com.berkay.build_logic.convention.extension.addKsp
import com.berkay.build_logic.convention.extension.configureAndroidCompose
import com.berkay.build_logic.convention.extension.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            project.pluginManager.apply {
                apply(plugin = PluginConstants.Plugins.AndroidApplication)
                apply(plugin = PluginConstants.Plugins.KotlinAndroid)
                apply(plugin = PluginConstants.Plugins.Hilt)
                apply(plugin = PluginConstants.Plugins.Ksp)
                apply(plugin = PluginConstants.Plugins.KotlinParcelize)
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)

                packaging {
                    resources {
                        excludes += "/META-INF/DEPENDENCIES"
                        excludes += "/META-INF/LICENSE*"
                        excludes += "/META-INF/NOTICE*"
                    }
                }

                defaultConfig {
                    applicationId = AppConfig.APPLICATION_ID
                    minSdk = AppConfig.MIN_SDK_VERSION
                    targetSdk = AppConfig.TARGET_SDK_VERSION
                    versionCode = AppConfig.VERSION_CODE
                    versionName = AppConfig.versionName

                    vectorDrawables {
                        useSupportLibrary = true
                    }

                    proguardFiles(
                        getDefaultProguardFile(PluginConstants.Defaults.ProguardAndroidRules),
                        PluginConstants.Defaults.ProguardRules
                    )
                }
            }

            dependencies {
                addImplementation(PluginConstants.Libraries.DaggerHilt)
                addKsp(PluginConstants.Libraries.DaggerHiltCompiler)

                addImplementation(PluginConstants.Libraries.AndroidxMaterial3)
                addImplementation(PluginConstants.Libraries.AndroidxCoreKtx)
                addImplementation(PluginConstants.Libraries.KotlinSerializationJson)
                addImplementation(PluginConstants.Libraries.AndroidxLifecycleRuntimeKtx)
                addImplementation(PluginConstants.Libraries.AndroidxActivityCompose)
                addImplementation(PluginConstants.Libraries.AndroidxUi)
                addImplementation(PluginConstants.Libraries.AndroidxUiGraphics)
                addImplementation(PluginConstants.Libraries.AndroidxUiToolingPreview)
                addImplementation(PluginConstants.Libraries.AndroidxNavigation)

                addDebugImplementation(PluginConstants.Libraries.AndroidxUiTooling)
                addDebugImplementation(PluginConstants.Libraries.AndroidxUiTestManifest)
                /*addTestImplementation(
                    library = FrameworkPluginConstants.Libraries.PaparaFrameworkTestkit,
                    versionCatalog = frameworkLibs
                )*/
            }
        }
    }
}
