package com.berkay.build_logic.convention.plugins

import com.android.build.api.dsl.LibraryExtension
import com.berkay.build_logic.convention.AppConfig
import com.berkay.build_logic.convention.PluginConstants
import com.berkay.build_logic.convention.extension.addImplementation
import com.berkay.build_logic.convention.extension.addKsp
import com.berkay.build_logic.convention.extension.addProject
import com.berkay.build_logic.convention.extension.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class AndroidDataLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            project.pluginManager.apply {
                apply(plugin = PluginConstants.Plugins.AndroidLibrary)
                apply(plugin = PluginConstants.Plugins.KotlinAndroid)
                apply(plugin = PluginConstants.Plugins.Ksp)
                apply(plugin = PluginConstants.Plugins.Hilt)
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                packaging {
                    resources {
                        excludes += "/META-INF/LICENSE.md"
                    }
                }
                tasks.withType<Test>().configureEach {
                    jvmArgs("-XX:+EnableDynamicAgentLoading")
                }
                defaultConfig {
                    minSdk = AppConfig.MIN_SDK_VERSION

                    vectorDrawables {
                        useSupportLibrary = true
                    }

                    proguardFiles(
                        getDefaultProguardFile(PluginConstants.Defaults.ProguardAndroidRules),
                        PluginConstants.Defaults.ProguardRules
                    )
                }

                dependencies {
                    addProject(PluginConstants.Projects.NETWORK)
                    addProject(PluginConstants.Projects.CORE_DOMAIN)
                    addProject(PluginConstants.Projects.CORE_DATA)
                    addImplementation(PluginConstants.Libraries.KotlinxCoroutinesCore)

                    addImplementation(PluginConstants.Libraries.DaggerHilt)
                    addKsp(PluginConstants.Libraries.DaggerHiltCompiler)
                }
            }
        }

    }

}