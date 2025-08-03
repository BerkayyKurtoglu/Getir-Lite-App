import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationConventionPlugin") {
            id = "gradlePlugins.androidApplicationConventionPlugin"
            implementationClass = "com.berkay.build_logic.convention.plugins.AndroidApplicationConventionPlugin"
        }
        register("androidDataLibraryConventionPlugin") {
            id = "gradlePlugins.androidDataLibraryConventionPlugin"
            implementationClass = "com.berkay.build_logic.convention.plugins.AndroidDataLibraryConventionPlugin"
        }
        register("androidDomainLibraryConventionPlugin") {
            id = "gradlePlugins.androidDomainLibraryConventionPlugin"
            implementationClass = "com.berkay.build_logic.convention.plugins.AndroidDomainLibraryConventionPlugin"
        }
        register("androidComposeLibraryConventionPlugin") {
            id = "gradlePlugins.androidComposeLibraryConventionPlugin"
            implementationClass = "com.berkay.build_logic.convention.plugins.AndroidComposeLibraryConventionPlugin"
        }
        register("kotlinLibraryConventionPlugin") {
            id = "gradlePlugins.kotlinLibraryConventionPlugin"
            implementationClass = "com.berkay.build_logic.convention.plugins.KotlinLibraryConventionPlugin"
        }
    }
}