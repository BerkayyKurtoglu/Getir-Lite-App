plugins {
    id(libs.plugins.custom.composeLibraryConventionPlugin.get().pluginId)
}

android {
    namespace = "com.berkay.feature.products.presentation"
}

dependencies {
    implementation(projects.feature.products.domain)
    implementation(projects.feature.common.domain)

    implementation(projects.feature.detail.contract)
    implementation(projects.feature.cart.contract)

    implementation(projects.core.presentation)
    implementation(projects.core.common)
}