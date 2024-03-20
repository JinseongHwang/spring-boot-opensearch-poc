import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
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
    implementation("org.opensearch.client:spring-data-opensearch-starter:1.3.0")
    implementation("org.opensearch.client:spring-data-opensearch:1.3.0")
    implementation("org.opensearch.client:opensearch-java:2.9.1")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.3.1")
    implementation("software.amazon.awssdk:opensearch:2.17.65")
    implementation("software.amazon.awssdk:url-connection-client:2.17+")

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
