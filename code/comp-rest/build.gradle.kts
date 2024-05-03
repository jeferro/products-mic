import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id ("com.jeferro.plugins.api-first-generator")
}

dependencies {
    api("org.springframework.boot", "spring-boot-starter-web")

    implementation("org.springframework.boot", "spring-boot-starter-actuator")

    api("jakarta.validation", "jakarta.validation-api", "3.0.2")
    implementation("org.openapitools", "jackson-databind-nullable", "0.2.6")

    api("org.springframework.boot", "spring-boot-starter-security")
    implementation("com.auth0", "java-jwt", Versions.jwt)
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

apiFirstGenerator {
    basePackage = "com.jeferro.products.components.rest.generated"
    specFile = file("${projectDir}/src/main/openapi/openapi.yml")
    targetDir = file("${projectDir}/build/generated-resources/openapi")
}
