plugins {
    kotlin("jvm")
}

version = rootProject.version
group = rootProject.group

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    val ktorVersion: String by project
    val kotestVersion: String by project
    val logbackVersion: String by project
    val ktDateTimeVersion: String by project

    implementation("io.ktor:ktor-gson:$ktorVersion")
    implementation(project(":builders-marketplace-multiplatform-pipelines"))
    implementation(project(":builders-marketplace-multiplatform-common"))
    implementation(project(":builders-marketplace-backend-common"))
    implementation(project(":builders-marketplace-multiplatform-pipelines-validation"))

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:$ktDateTimeVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    testImplementation("io.kotest:kotest-assertions-ktor:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")

    tasks.named<Test>("test") { // or "jvmTest" etc
        useJUnitPlatform()
        systemProperty("allure.results.directory", project.buildDir.toString() + "/allure-results")
    }
}
