plugins {
    id(libs.plugins.custom.composeLibraryConventionPlugin.get().pluginId)
}

android {
    namespace = "com.berkay.feature.count.presentation"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.presentation)
    implementation(projects.feature.common.domain)
}