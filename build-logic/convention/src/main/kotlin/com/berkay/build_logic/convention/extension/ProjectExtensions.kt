package com.berkay.build_logic.convention.extension

import com.android.build.api.dsl.CommonExtension
import com.berkay.build_logic.convention.AppConfig
import com.berkay.build_logic.convention.PluginConstants
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

internal val Project.libs
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.configureKotlin() {
    tasks.withType<KotlinJvmCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0)
        }
    }
}

internal fun Project.addImplementation(library: String, versionCatalog: VersionCatalog = libs) {
    dependencies {
        add("implementation", versionCatalog.findLibrary(library).get())
    }
}

internal fun Project.addKsp(library: String, versionCatalog: VersionCatalog = libs) {
    dependencies {
        add("ksp", versionCatalog.findLibrary(library).get())
    }
}

internal fun Project.addTestImplementation(library: String, versionCatalog: VersionCatalog = libs) {
    dependencies {
        add("testImplementation", versionCatalog.findLibrary(library).get())
    }
}

internal fun Project.addDebugImplementation(
    library: String,
    versionCatalog: VersionCatalog = libs
) {
    dependencies {
        add("debugImplementation", versionCatalog.findLibrary(library).get())
    }
}

internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    commonExtension.apply {
        compileSdk = AppConfig.COMPILE_SDK_VERSION

        defaultConfig {
            minSdk = AppConfig.MIN_SDK_VERSION
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        configureKotlin()
    }
}

internal fun Project.configureAndroidCompose(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    project.pluginManager.apply {
        apply(plugin = PluginConstants.Plugins.KotlinCompose)
    }

    commonExtension.apply {
        buildFeatures {
            compose = true
            buildConfig = true
        }
    }

    dependencies {
        addImplementation(PluginConstants.Libraries.AndroidxMaterial3)
        addImplementation(PluginConstants.Libraries.AndroidxCoreKtx)
        addImplementation(PluginConstants.Libraries.HiltNavigationCompose)
        //addImplementation(PluginConstants.Libraries2.KotlinSerializationJson)
        addImplementation(PluginConstants.Libraries.AndroidxLifecycleRuntimeKtx)
        addImplementation(PluginConstants.Libraries.AndroidxActivityCompose)
        addImplementation(PluginConstants.Libraries.AndroidxUi)
        addImplementation(PluginConstants.Libraries.AndroidxUiGraphics)
        addImplementation(PluginConstants.Libraries.AndroidxUiToolingPreview)
        addImplementation(PluginConstants.Libraries.AndroidxNavigation)

        addDebugImplementation(PluginConstants.Libraries.AndroidxUiTooling)
        addDebugImplementation(PluginConstants.Libraries.AndroidxUiTestManifest)
    }
}

