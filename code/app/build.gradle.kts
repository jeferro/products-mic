plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation(project(":comp-mongodb"))
    implementation(project(":comp-kafka"))
    implementation(project(":comp-rest"))

    implementation("org.apache.commons", "commons-lang3", Versions.commons_lang3)

    testImplementation("org.springframework.boot", "spring-boot-starter-test")
    testImplementation("org.springframework.boot", "spring-boot-testcontainers")
    testImplementation("org.testcontainers", "junit-jupiter")
    testImplementation("org.testcontainers", "mongodb", Versions.test_containers)
}

tasks.withType<Test> {
    useJUnitPlatform()
}