plugins {
    id(libs.plugins.custom.composeLibraryConventionPlugin.get().pluginId)
}

android {
    namespace = "com.berkay.feature.products.presentation"
}

dependencies {
    implementation(projects.feature.products.domain)
    implementation(projects.feature.common.domain)
}