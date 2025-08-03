package com.berkay.build_logic.convention.plugins

import com.berkay.build_logic.convention.PluginConstants
import com.berkay.build_logic.convention.extension.addImplementation
import com.berkay.build_logic.convention.extension.addProject
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidDomainLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply(plugin = PluginConstants.Defaults.JavaLibrary)
                apply(plugin = PluginConstants.Plugins.JetbrainsKotlinJvm)
            }

            extensions.configure<JavaPluginExtension> {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            tasks.withType<JavaCompile>().configureEach {
                sourceCompatibility = JavaVersion.VERSION_17.toString()
                targetCompatibility = JavaVersion.VERSION_17.toString()
            }

            tasks.withType<KotlinCompile>().configureEach {
                compilerOptions {
                    jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
                }
            }

            dependencies {
                addProject(PluginConstants.Projects.CORE_DOMAIN)
                addImplementation(PluginConstants.Libraries.KotlinxCoroutinesCore)
            }
        }
    }
}