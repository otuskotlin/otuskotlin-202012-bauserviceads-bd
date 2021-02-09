import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") apply false
    kotlin("multiplatform") apply false
}

subprojects {

    repositories {
        // Use JCenter for resolving dependencies.
        jcenter()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    tasks.withType<Wrapper> {
        gradleVersion = "6.6.1"
    }
}


