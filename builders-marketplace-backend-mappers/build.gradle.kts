plugins {
    kotlin("jvm")
    id("io.qameta.allure")
    id("org.jlleitschuh.gradle.ktlint")
}

group = rootProject.group
version = rootProject.version

allure {
    val allureVersion: String by project
    autoconfigure = false
    version = allureVersion
}

repositories {
    mavenCentral()
}

dependencies {
    val kotestVersion: String by project

    // Align versions of all Kotlin components
    implementation(kotlin("stdlib"))
    implementation(project(":builders-marketplace-backend-common"))
    implementation(project(":builders-marketplace-transport-multiplatform"))

    testImplementation("io.kotest:kotest-assertions-ktor:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
}

tasks.named<Test>("test") { // or "jvmTest" etc
    useJUnitPlatform()
    systemProperty("allure.results.directory", project.buildDir.toString() + "/allure-results")
}
