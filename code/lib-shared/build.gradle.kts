import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("io.freefair.lombok") version "8.10.2"
}

dependencies {
    // General
    api("org.apache.commons", "commons-lang3", Versions.commons_lang3)

    api("org.mapstruct", "mapstruct", Versions.mapstruct)
    annotationProcessor("org.mapstruct", "mapstruct-processor", Versions.mapstruct)

    // Spring
    implementation("org.springframework.boot", "spring-boot-starter")
    api("org.springframework.boot", "spring-boot-starter-security")

    // Rest
    api("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-actuator")

    implementation("com.auth0", "java-jwt", Versions.jwt)

    // Mongo
    api("org.springframework.boot", "spring-boot-starter-data-mongodb")

    // Kafka
    api("org.springframework.kafka", "spring-kafka", Versions.spring_kafka)

    api("org.apache.avro", "avro", Versions.avro)
    implementation("io.confluent", "kafka-avro-serializer", Versions.kafka_avro_serializer)
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
