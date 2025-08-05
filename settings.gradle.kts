pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "GetirLite"
include(":app")
include(":ui")
include(":core")
include(":core:common")
include(":network")
include(":core:contract")
include(":core:domain")
include(":core:data")
include(":feature")
include(":feature:products")
include(":feature:products:data")
include(":feature:products:domain")
include(":feature:products:contract")
include(":feature:products:presentation")
include(":feature:common")
include(":feature:common:domain")
include(":feature:common:data")
include(":core:presentation")

include(":feature:detail")
include(":feature:detail:presentation")
include(":feature:detail:contract")

include(":feature:cart")
include(":feature:cart:presentation")
include(":feature:cart:contract")
include(":test")
