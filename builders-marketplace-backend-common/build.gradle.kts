import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

dependencies {
    val ktorVersion: String by project
    val kotestVersion: String by project
    val config4kVersion: String by project
    val logbackVersion: String by project
    val guavaVersion: String by project
    val gsonVersion: String by project

    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("io.ktor:ktor-gson:$ktorVersion")

    implementation("io.github.config4k:config4k:$config4kVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:$guavaVersion")
    implementation("com.google.code.gson:gson:$gsonVersion")

    testImplementation("io.kotest:kotest-assertions-ktor:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")
}

tasks.named<Test>("test") { // or "jvmTest" etc
    useJUnitPlatform()
    systemProperty("allure.results.directory", project.buildDir.toString() + "/allure-results")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
}