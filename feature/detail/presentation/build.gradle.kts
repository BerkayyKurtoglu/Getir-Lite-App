plugins {
    id(libs.plugins.custom.composeLibraryConventionPlugin.get().pluginId)
}

android {
    namespace = "com.berkay.feature.detail.presentation"
}

dependencies {
    implementation(projects.feature.detail.contract)
    implementation(projects.feature.common.domain)
    implementation(projects.core.common)
    implementation(projects.core.presentation)

    implementation(libs.coil.compose)
    implementation(libs.coil)
}