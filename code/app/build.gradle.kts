plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("jacoco")
}

dependencies {
    implementation(project(":components:comp-mongodb"))
    implementation(project(":components:comp-kafka"))
    implementation(project(":components:comp-rest"))

    implementation("org.apache.commons", "commons-lang3", Versions.commons_lang3)

    implementation("org.mapstruct", "mapstruct", Versions.mapstruct)
    annotationProcessor("org.mapstruct", "mapstruct-processor", Versions.mapstruct)

    testImplementation("org.springframework.boot", "spring-boot-starter-test")
    testImplementation("org.springframework.boot", "spring-boot-testcontainers")
    testImplementation("org.testcontainers", "junit-jupiter")
    testImplementation("org.testcontainers", "mongodb", Versions.test_containers)

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
                        "**/commands/**",
                        "**/mappers/**"
                    )
                }
            })
        )
    }
}


