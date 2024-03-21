import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
}

group = "me.study"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven(url = "https://aws.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    // opensearch
    implementation("org.opensearch.client:opensearch-java:2.7.0")
    implementation("org.opensearch.client:spring-data-opensearch:1.2.0")
    implementation("org.opensearch.client:spring-data-opensearch-starter:1.2.0") {
        exclude(group = "org.opensearch.client", module = "opensearch-rest-client-sniffer")
    }
    implementation("software.amazon.awssdk:opensearch:2.25.13")

    // spring boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
