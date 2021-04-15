plugins {
    kotlin("multiplatform")
    id("io.qameta.allure")
    id("org.jlleitschuh.gradle.ktlint")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

kotlin {
    val logbackVersion: String by project
    val kotestVersion: String by project

    js {
        browser()
        nodejs()
    }
    jvm {
        withJava()
    }

    sourceSets {
        val coroutinesVersion: String by project
        val ktDateTimeVersion: String by project
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$coroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$ktDateTimeVersion")

                // Align versions of all Kotlin components
                implementation("ch.qos.logback:logback-classic:$logbackVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$coroutinesVersion")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(npm("is-sorted", "1.0.5"))
                implementation(npm("mathjs", "7.2.0"))
                implementation(kotlin("test"))
                implementation(kotlin("test-js"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation("io.kotest:kotest-assertions-ktor:$kotestVersion")
                implementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
            }
        }
    }
}
