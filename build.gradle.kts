plugins {
    kotlin("jvm") version "2.2.0"
    id("io.ktor.plugin") version "3.2.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    implementation("io.ktor:ktor-server-core:3.2.0")
    implementation("io.ktor:ktor-server-netty:3.2.0")
    implementation("io.ktor:ktor-server-cors:3.2.0")
    implementation("io.ktor:ktor-server-content-negotiation:3.2.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.2.0")
}
