import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    // General
    api("org.apache.commons", "commons-lang3", Versions.commons_lang3)

    api("org.mapstruct", "mapstruct", Versions.mapstruct)
    annotationProcessor("org.mapstruct", "mapstruct-processor", Versions.mapstruct)

    // Spring
    implementation("org.springframework.boot", "spring-boot-starter")
    implementation("org.springframework.boot", "spring-boot-starter-security")

    // Rest
    api("org.springframework.boot", "spring-boot-starter-web")
    implementation("com.auth0", "java-jwt", Versions.jwt)

    api("jakarta.validation", "jakarta.validation-api", "3.0.2")
    api("org.openapitools", "jackson-databind-nullable", "0.2.6")

    // Mongo
    api("org.springframework.boot", "spring-boot-starter-data-mongodb")

    // Kafka
    api("org.springframework.kafka", "spring-kafka", Versions.spring_kafka)
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
