plugins {
    id(libs.plugins.custom.dataLibraryConventionPlugin.get().pluginId)
}

android {
    namespace = "com.berkay.feature.products.data"
}

dependencies {
    implementation(projects.feature.products.domain)
}