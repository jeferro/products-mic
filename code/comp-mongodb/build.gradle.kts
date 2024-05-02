import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    api("org.springframework.boot", "spring-boot-starter-data-mongodb")

    implementation("org.springframework.boot", "spring-boot-starter-security")

    testImplementation("org.springframework.boot", "spring-boot-starter-test")
    testImplementation("org.springframework.boot", "spring-boot-testcontainers")
    testImplementation("org.testcontainers", "junit-jupiter")
    testImplementation("org.testcontainers", "mongodb", Versions.test_containers)
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
