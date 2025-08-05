plugins {
    id(libs.plugins.custom.dataLibraryConventionPlugin.get().pluginId)
    alias(libs.plugins.android.junit5)
}

android {
    namespace = "com.berkay.feature.common.data"
}

dependencies {
    implementation(projects.feature.common.domain)

    testImplementation(libs.bundles.test.all)
    testImplementation(platform(libs.test.junit.bom))
    testImplementation(kotlin("test"))
    testImplementation(projects.test)
}