plugins {
    id(libs.plugins.custom.applicationConventionPlugin.get().pluginId)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.berkay.getirlite"
    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://65c38b5339055e7482c12050.mockapi.io/\"")
        }

        release {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://65c38b5339055e7482c12050.mockapi.io/\"")
        }
    }
}

dependencies {
    implementation(projects.ui)

    implementation(projects.core.common)
    implementation(projects.core.contract)
    implementation(projects.core.domain)

    implementation(projects.feature.common.data)
    implementation(projects.feature.common.domain)

    implementation(projects.feature.products.contract)
    implementation(projects.feature.products.data)
    implementation(projects.feature.products.domain)
    implementation(projects.feature.products.presentation)

    implementation(projects.feature.detail.contract)
    implementation(projects.feature.detail.presentation)

    implementation(projects.feature.cart.contract)
    implementation(projects.feature.cart.presentation)
}