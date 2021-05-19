plugins {
    application
    kotlin("jvm")
    id("io.qameta.allure")
}

group = rootProject.group
version = rootProject.version

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

allure {
    val allureVersion: String by project
    autoconfigure = false
    version = allureVersion
}

repositories {
    mavenCentral()
}

dependencies {
    val ktorVersion: String by project
    val kotestVersion: String by project
    val config4kVersion: String by project
    val logbackVersion: String by project
    val ktorKafkaVersion: String by project
    val kafkaVersion: String by project

    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(kotlin("stdlib"))
    implementation(project(":builders-marketplace-transport-multiplatform"))
    implementation(project(":builders-marketplace-backend-mappers"))
    implementation(project(":builders-marketplace-backend-common"))
    implementation(project(":builders-marketplace-backend-business-logic"))
    implementation(project(":builders-marketplace-multiplatform-common"))

    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("io.ktor:ktor-gson:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    implementation("io.github.config4k:config4k:$config4kVersion")

    implementation("com.github.Datana-company:ktor-kafka:$ktorKafkaVersion")
    implementation("org.apache.kafka:kafka-clients:$kafkaVersion")

    testImplementation("io.kotest:kotest-assertions-ktor:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")
}

tasks.named<Test>("test") { // or "jvmTest" etc
    useJUnitPlatform()
    systemProperty("allure.results.directory", project.buildDir.toString() + "/allure-results")
}
