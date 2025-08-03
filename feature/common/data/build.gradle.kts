plugins {
    id(libs.plugins.custom.dataLibraryConventionPlugin.get().pluginId)
}

android {
    namespace = "com.berkay.feature.common.data"
}

dependencies {
    implementation(projects.feature.common.domain)
}