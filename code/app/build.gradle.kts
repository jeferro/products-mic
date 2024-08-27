plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("jacoco")
}

dependencies {
    implementation(project(":library-shared"))

    implementation(project(":comp-mongodb"))
    implementation(project(":comp-kafka"))
    implementation(project(":comp-rest"))

    testImplementation("org.springframework.boot", "spring-boot-starter-test")
    testImplementation("org.springframework.boot", "spring-boot-testcontainers")
    testImplementation("org.testcontainers", "junit-jupiter")
    testImplementation("org.testcontainers", "mongodb", Versions.test_containers)

    annotationProcessor("org.mapstruct", "mapstruct-processor", Versions.mapstruct)

    testImplementation("com.approvaltests", "approvaltests", Versions.approval_tests)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.compilerArgs = listOf(
        "-Amapstruct.unmappedTargetPolicy=ERROR"
    )
}

jacoco {
    toolVersion = Versions.jacoco
}

tasks.withType<JacocoReport> {
    afterEvaluate {
        classDirectories.setFrom(
            files(classDirectories.files.map {
                fileTree(it).apply {
                    exclude(
                        "**/Application*",
                        "**/*Configuration*",
                        "**/dtos/**",
                        "**/daos/**",
                        "**/params/**",
                        "**/mappers/**"
                    )
                }
            })
        )
    }
}


