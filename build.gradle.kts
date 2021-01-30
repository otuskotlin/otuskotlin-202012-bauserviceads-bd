import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
    id("io.qameta.allure") version "2.8.1"
    id("org.jlleitschuh.gradle.ktlint") version "9.4.1"

    java
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

val ktorVersion = "1.5.0"
val kotestVersion = "4.3.2"
val logbackVersion = "1.2.3"
val kotlinSerialization = "0.20.0"
val config4kVersion = "0.4.2"

allure {
    autoconfigure = false
    version = "2.13.1"
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("io.qameta.allure")
        plugin("org.jlleitschuh.gradle.ktlint")
    }

    repositories {
        // Use JCenter for resolving dependencies.
        jcenter()
    }

    dependencies {
        // Align versions of all Kotlin components
        implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

        implementation("io.ktor:ktor-server-core:$ktorVersion")
        implementation("io.ktor:ktor-server-netty:$ktorVersion")
        implementation("io.ktor:ktor-serialization:$ktorVersion")
        implementation("io.ktor:ktor-gson:$ktorVersion")

        implementation("io.github.config4k:config4k:$config4kVersion")
        implementation("ch.qos.logback:logback-classic:$logbackVersion")

        // This dependency is used by the application.
        implementation("com.google.guava:guava:29.0-jre")
        implementation("com.google.code.gson:gson:2.8.6")

        testImplementation("io.kotest:kotest-assertions-ktor:$kotestVersion")
        testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
        testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")

    }

    tasks.named<Test>("test") { // or "jvmTest" etc
        useJUnitPlatform()
        systemProperty("allure.results.directory", project.buildDir.toString() + "/allure-results")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

application {
    // Define the main class for the application.
    mainClass.set("builders.marketplace.AppKt")
}

tasks.withType<Wrapper> {
    gradleVersion = "6.7.1"
}
