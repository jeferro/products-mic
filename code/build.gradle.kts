plugins {
    java
    id("org.springframework.boot") version Versions.spring_boot
    id("io.spring.dependency-management") version Versions.spring_dependency_management
}

group = "com.jeferro"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons", "commons-lang3", Versions.commons_lang3)

    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-actuator")
    implementation("org.springframework.boot", "spring-boot-starter-data-mongodb")
    implementation("org.springframework.kafka", "spring-kafka", Versions.spring_kafka)
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
    testImplementation("org.springframework.boot", "spring-boot-testcontainers")
    testImplementation("org.testcontainers", "junit-jupiter")
    testImplementation("org.testcontainers", "mongodb", Versions.test_containers)

    implementation("com.auth0", "java-jwt", Versions.jwt)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
