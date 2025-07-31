plugins {
    id(libs.plugins.custom.applicationConventionPlugin.get().pluginId)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.berkay.getirlite"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(projects.ui)
    implementation(projects.core.common)
}