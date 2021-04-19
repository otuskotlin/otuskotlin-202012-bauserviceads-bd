rootProject.name = "builders-marketplace"

pluginManagement {
    plugins {
        val allurePluginVersion: String by settings
        val ktlintVersion: String by settings
        val kotlinVersion: String by settings
        // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
        kotlin("jvm") version kotlinVersion apply false
        kotlin("multiplatform") version kotlinVersion apply false
        kotlin("plugin.serialization") version kotlinVersion apply false
        id("io.qameta.allure") version allurePluginVersion apply false
        id("org.jlleitschuh.gradle.ktlint") version ktlintVersion apply false

        java apply false
        // Apply the application plugin to add support for building a CLI application in Java.
        application apply false
    }
}

include("builders-marketplace-backend-common")
include("builders-marketplace-multiplatform-common")
include("builders-marketplace-transport-multiplatform")
include("builders-marketplace-backend-mappers")
include("builders-marketplace-app-ktor")
include("builders-marketplace-multiplatform-pipelines")
include("builders-marketplace-backend-business-logic")
include("builders-marketplace-multiplatform-pipelines-validation")
