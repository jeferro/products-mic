import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id ("org.openapi.generator")
}

dependencies {
    api("org.springframework.boot", "spring-boot-starter-web")

    implementation("org.springframework.boot", "spring-boot-starter-actuator")

    api("jakarta.validation", "jakarta.validation-api")
    implementation("org.openapitools", "jackson-databind-nullable", Versions.jackson_databind_nullable)

    api("org.springframework.boot", "spring-boot-starter-security")
    implementation("com.auth0", "java-jwt", Versions.jwt)
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

tasks.withType<JavaCompile> {
    dependsOn(tasks.openApiGenerate)
}

sourceSets {
    main {
        java {
            srcDir("$projectDir/build/generate-resources/main/src/main/java")
        }
        resources {
            srcDir("$projectDir/src/main/openapi/")
        }
    }
}

openApiGenerate {
    val basePackage = "com.jeferro.products.components.products.rest"

    generatorName.set("spring")
    inputSpec.set("$projectDir/src/main/openapi/openapi.yml")

    packageName.set(basePackage)
    apiPackage.set("${basePackage}.apis")
    modelPackage.set("${basePackage}.dtos")
    modelNameSuffix.set("RestDTO")
    apiNameSuffix.set("Api")

    generateApiTests.set(false)
    configOptions.set(mapOf(
        "useTags" to "true",
        "gradleBuildFile" to "false",
        "useSpringBoot3" to "true",
        "documentationProvider" to "none",
        "interfaceOnly" to "true"
    ))
}
