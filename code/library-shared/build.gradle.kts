import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    api("org.apache.commons", "commons-lang3", Versions.commons_lang3)

    implementation("org.springframework.boot", "spring-boot-starter")
    implementation("org.springframework.boot", "spring-boot-starter-security")

    api("org.springframework.boot", "spring-boot-starter-web")

    api("org.springframework.boot", "spring-boot-starter-data-mongodb")

    api("org.springframework.kafka", "spring-kafka", Versions.spring_kafka)

    api("org.mapstruct", "mapstruct", Versions.mapstruct)
    annotationProcessor("org.mapstruct", "mapstruct-processor", Versions.mapstruct)
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
