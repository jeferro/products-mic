plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.jeferro.plugins.avro-generator")
    id("jacoco")
}

dependencies {
    implementation(project(":library-shared"))

    annotationProcessor("org.mapstruct", "mapstruct-processor", Versions.mapstruct)

    testImplementation("org.springframework.boot", "spring-boot-starter-test")

    testImplementation("org.springframework.boot", "spring-boot-testcontainers")
    testImplementation("org.testcontainers", "junit-jupiter")

    implementation(project(":comp-rest"))

    implementation(project(":comp-mongodb"))
    testImplementation("org.testcontainers", "mongodb", Versions.test_containers)

    testImplementation("com.approvaltests", "approvaltests", Versions.approval_tests)
}

tasks.withType<Test> {
    useJUnitPlatform()
}


// Mapstruct
tasks.withType<JavaCompile> {
    options.compilerArgs = listOf(
        "-Amapstruct.unmappedTargetPolicy=ERROR"
    )
}


// Avro
avroGenerator {
    schemaDir = file("${projectDir}/../../apis/avro")
    targetDir = file("${projectDir}/build/generated/sources/avro")
}


// Jacoco
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


