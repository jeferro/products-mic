plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.jeferro.plugins.avro-generator")
    id ("com.jeferro.plugins.api-first-generator")
    id("jacoco")
}

dependencies {
    // General
    implementation(project(":library-shared"))

    annotationProcessor("org.mapstruct", "mapstruct-processor", Versions.mapstruct)

    testImplementation("com.approvaltests", "approvaltests", Versions.approval_tests)

    // Spring
    testImplementation("org.springframework.boot", "spring-boot-starter-test")

    testImplementation("org.springframework.boot", "spring-boot-testcontainers")
    testImplementation("org.testcontainers", "junit-jupiter")

    // Rest
    api("jakarta.validation", "jakarta.validation-api", Versions.jakarta_validation_api)
    implementation("org.openapitools", "jackson-databind-nullable", Versions.jackson_databind_nullable)

    // Mongo
    testImplementation("org.testcontainers", "mongodb", Versions.test_containers)
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


// Rest
apiFirstGenerator {
    basePackage = "com.jeferro.products.components.rest.generated"
    specFile = file("${projectDir}/../../apis/rest/openapi.yml")
    targetDir = file("${projectDir}/build/generated-resources/openapi")
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


