package com.berkay.build_logic.convention

object PluginConstants {
    object Defaults {
        const val Application = "com.android.application"
        const val Android = "org.jetbrains.kotlin.android"
        const val ComposePlugin = "org.jetbrains.kotlin.plugin.compose"
        const val TestInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val ConsumerRules = "consumer-rules.pro"
        const val JavaLibrary = "java-library"
        const val MavenPublish = "maven-publish"
        const val ProguardRules = "proguard-rules.pro"
        const val ProguardAndroidRules = "proguard-android.txt"
    }

    object Versions {
        // Kotlin & Gradle Plugins
        const val Kotlin = "2.0.0"
        const val Kotlin_gradle_plugin = "1.8.20"
        const val Tools_build_gradle = "8.4.1"
        const val Plugin_hilt = "2.50"

        // Kotlinx & Coroutines
        const val Kotlinx_coroutines = "1.7.3"
        const val Test_kotlinx_coroutines = "1.7.3"

        // AndroidX Libraries
        const val Core_ktx = "1.13.1"
        const val Activity_ktx = "1.9.0"
        const val Fragment_ktx = "1.7.1"
        const val Appcompat = "1.7.0"

        // Google & UI
        const val Material = "1.12.0"
        const val Android_installreferrer = "2.2"
        const val Airbnb_lottie = "6.1.0"

        // JUnit & Testing
        const val Test_junit_bom = "5.10.0"
        const val Test_junit_platform_runner = "1.10.0"
        const val Test_junit_jupiter_api = "5.10.0"
        const val Test_junit_jupiter_engine = "5.10.0"
        const val Test_junit_4 = "4.13.2"
        const val Test_arch_testing = "2.2.0"
        const val Test_json = "20220924"
        const val Test_mockito_kotlin = "3.2.0"
        const val Test_mockk = "1.13.4"
        const val Test_turbine = "1.0.0"
    }

    object Projects {
        const val NETWORK = ":network"
        const val CORE_DATA = ":core:data"
        const val CORE_DOMAIN = ":core:domain"
        const val UI = ":ui"
        const val TEST = ":test"
    }

    object Libraries {
        // Compose & AndroidX
        const val AndroidxComposeBom = "androidx.compose.bom"
        const val AndroidxCoreKtx = "androidx-core-ktx"
        const val AndroidxActivityCompose = "androidx-activity-compose"
        const val AndroidxActivityKtx = "androidx.activity.ktx"
        const val AndroidxAppcompat = "androidx.appcompat"
        const val AndroidxAppcompatResources = "androidx.appcompat.resources"
        const val AndroidxMaterial3 = "androidx-material3"
        const val AndroidxLifecycleRuntimeKtx = "androidx-lifecycle-runtime-ktx"
        const val AndroidxUi = "androidx-ui"
        const val AndroidxUiGraphics = "androidx-ui-graphics"
        const val AndroidxUiTooling = "androidx-ui-tooling"
        const val AndroidxUiToolingPreview = "androidx-ui-tooling-preview"
        const val AndroidxUiTestManifest = "androidx-ui-test-manifest"
        const val AndroidxUiTestJunit4 = "androidx.ui.test.junit4"
        const val AndroidxNavigation = "androidx-navigation"
        const val ComposeConstraintLayout = "androidx-constraintlayout-compose"
        const val ComposeAnimation = "androidx-animation"

        // Kotlin & Coroutines
        const val KotlinGradle = "kotlin.gradle"
        const val KotlinReflect = "kotlin.reflect"
        const val KotlinImmutableList = "kotlin.immutable.list"
        const val KotlinxCoroutinesCore = "kotlinx.coroutines.core"
        const val KotlinxCoroutinesAndroid = "kotlinx.coroutines.android"
        const val KotlinxCoroutines = "kotlinx.coroutines"
        const val KotlinSerializationJson = "kotlinx-serialization-json"
        const val KotlinxCollectionsImmutable = "kotlinx-collections-immutable"

        // Gradle Plugins
        const val AndroidGradlePlugin = "android.gradleplugin"
        const val AndroidToolsGradle = "android.tools.gradle"
        const val KspGradlePlugin = "ksp.gradleplugin"

        // Hilt
        const val DaggerHilt = "hilt-android"
        const val DaggerHiltCompiler = "hilt-compiler"
        const val HiltNavigationCompose = "hilt-navigation-compose"

        // Network
        const val NetworkOkhttpLib = "network.okhttp.lib"
        const val NetworkRetrofit = "network.retrofit"
        const val NetworkGsonConverter = "network.gson.converter"
        const val NetworkOkhttpBom = "network.okhttp.bom"
        const val NetworkOkhttp = "network.okhttp"
        const val NetworkOkhttpLogging = "network.okhttp.logging"
        const val NetworkChuckerLibrary = "network.chucker.library"
        const val NetworkChuckerLibraryNoop = "network.chucker.library.noop"

        // Google
        const val GoogleGson = "google.gson"

        // Coil
        const val COIL = "coil"
        const val COIL_COMPOSE = "coil-compose"

        // Test
        const val JUNIT = "junit"
        const val ANDROIDX_JUNIT = "androidx-junit"
        const val ANDROIDX_ESPRESSO_CORE = "androidx-espresso-core"
        const val TEST_JUNIT_BOM = "test-junit-bom"
        const val TEST_JUNIT_PLATFORM_RUNNER = "test-junit-platform-runner"
        const val TEST_JUNIT_JUPITER = "test-junit-jupiter"
        const val TEST_JUNIT_JUPITER_API = "test-junit-jupiter-api"
        const val TEST_JUNIT_JUPITER_ENGINE = "test-junit-jupiter-engine"
        const val TEST_JUNIT_JUPITER_PARAMS = "test-junit-jupiter-params"
        const val TEST_JUNIT_FOUR = "test-junit-four"
        const val TEST_CORE_TESTING = "test-core-testing"
        const val TEST_JSON = "test-json"
        const val TEST_MOCKITO_KOTLIN = "test-mockito-kotlin"
        const val TEST_KOTLINX_COROUTINES = "test-kotlinx-coroutines"
        const val TEST_MOCKK = "test-mockk"
        const val TEST_TURBINE = "test-turbine"
        const val TestAll = "test-all"
    }

    object Plugins {
        // Android Gradle Plugins
        const val AndroidApplication = "com.android.application"
        const val AndroidLibrary = "com.android.library"
        const val AndroidJunit5  = "de.mannodermaus.android-junit5"

        // Kotlin Plugins
        const val JetbrainsKotlinJvm = "org.jetbrains.kotlin.jvm"
        const val KotlinAndroid = "org.jetbrains.kotlin.android"
        const val KotlinParcelize = "org.jetbrains.kotlin.plugin.parcelize"
        const val KotlinCompose = "org.jetbrains.kotlin.plugin.compose"
        const val KotlinSerialization = "org.jetbrains.kotlin.plugin.serialization"

        // Dependency Injection
        const val Hilt = "com.google.dagger.hilt.android"
        const val Ksp = "com.google.devtools.ksp"
    }
}
