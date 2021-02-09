
/*
 * This file was generated by the Gradle 'init' task.
 *
 * The settings file is used to specify which projects to include in your build.
 *
 * Detailed information about configuring a multi-project build in Gradle can be found
 * in the user manual at https://docs.gradle.org/6.7.1/userguide/multi_project_builds.html
 */

rootProject.name = "builders-marketplace"

pluginManagement {
    plugins {
        val allurePluginVersion: String by settings
        val ktlintVersion: String by settings
        val kotlinVersion: String by settings
        // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
        kotlin("jvm") version kotlinVersion apply false
        kotlin("multiplatform") version kotlinVersion apply false
        id("io.qameta.allure") version allurePluginVersion apply false
        id("org.jlleitschuh.gradle.ktlint") version ktlintVersion

        java apply false
        // Apply the application plugin to add support for building a CLI application in Java.
        application apply false
    }
}

include("backend-common")
include("multiplatform-common")
