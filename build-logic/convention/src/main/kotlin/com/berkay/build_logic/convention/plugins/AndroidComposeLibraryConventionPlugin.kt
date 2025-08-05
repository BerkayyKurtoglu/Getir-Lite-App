package com.berkay.build_logic.convention.plugins

import com.android.build.api.dsl.LibraryExtension
import com.berkay.build_logic.convention.AppConfig
import com.berkay.build_logic.convention.PluginConstants
import com.berkay.build_logic.convention.extension.addImplementation
import com.berkay.build_logic.convention.extension.addKsp
import com.berkay.build_logic.convention.extension.addProject
import com.berkay.build_logic.convention.extension.addTestImplementation
import com.berkay.build_logic.convention.extension.addTestPlatformImplementation
import com.berkay.build_logic.convention.extension.configureAndroidCompose
import com.berkay.build_logic.convention.extension.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class AndroidComposeLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            project.pluginManager.apply {
                apply(plugin = PluginConstants.Plugins.AndroidLibrary)
                apply(plugin = PluginConstants.Plugins.KotlinAndroid)
                apply(plugin = PluginConstants.Plugins.KotlinSerialization)
                apply(plugin = PluginConstants.Plugins.Ksp)
                apply(plugin = PluginConstants.Plugins.Hilt)
                apply(plugin = PluginConstants.Plugins.AndroidJunit5)
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)

                packaging {
                    resources {
                        excludes += "/META-INF/LICENSE.md"
                    }
                }

                // Suppress Byte-Buddy agent warnings in unit tests
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
            }

            dependencies {
                addProject(PluginConstants.Projects.CORE_DOMAIN)
                addProject(PluginConstants.Projects.UI)
                addProject(PluginConstants.Projects.TEST)

                addImplementation(PluginConstants.Libraries.KotlinSerializationJson)
                addImplementation(PluginConstants.Libraries.KotlinxCollectionsImmutable)
                addImplementation(PluginConstants.Libraries.ComposeConstraintLayout)
                addImplementation(PluginConstants.Libraries.COIL)
                addImplementation(PluginConstants.Libraries.COIL_COMPOSE)
                addImplementation(PluginConstants.Libraries.DaggerHilt)
                addKsp(PluginConstants.Libraries.DaggerHiltCompiler)

                addTestImplementation(PluginConstants.Libraries.TEST_JUNIT_JUPITER_API)
                addTestImplementation(PluginConstants.Libraries.TEST_JUNIT_JUPITER_ENGINE)
                addTestImplementation(PluginConstants.Libraries.TEST_JUNIT_JUPITER_PARAMS)
                addTestImplementation(PluginConstants.Libraries.TEST_JUNIT_PLATFORM_RUNNER)
                addTestImplementation(PluginConstants.Libraries.TEST_KOTLINX_COROUTINES)
                addTestImplementation(PluginConstants.Libraries.TEST_MOCKK)
                addTestImplementation(PluginConstants.Libraries.TEST_TURBINE)
                addTestPlatformImplementation(PluginConstants.Libraries.TEST_JUNIT_BOM)
            }
        }
    }

}
