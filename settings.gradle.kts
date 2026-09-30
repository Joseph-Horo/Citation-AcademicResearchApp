pluginManagement {
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
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Citation"
include(":app")
include(":feature:home:data")

include(":feature:details:data")
include(":feature:explore:data")

include(":feature:details:presentation")
include(":feature:explore:presentation")
include(":feature:home:presentation")
include(":feature:watchlist:presentation")
include(":feature:watchlist:data")

include(":feature:details:domain")
include(":feature:explore:domain")
include(":feature:home:domain")
include(":feature:watchlist:domain")
include(":core:common")
include(":core:database")
include(":core:resource")
include(":core:di")
include(":core:network")
