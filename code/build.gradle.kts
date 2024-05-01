plugins {
    java
    id("org.springframework.boot") version Versions.spring_boot
    id("io.spring.dependency-management") version Versions.spring_dependency_management
    id("com.jeferro.plugins.avro-generator")
}

group = "com.jeferro"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://packages.confluent.io/maven/")
    }
}

dependencies {
    implementation("org.apache.commons", "commons-lang3", Versions.commons_lang3)

    implementation("org.springframework.boot", "spring-boot-starter-actuator")
    implementation("org.springframework.boot", "spring-boot-starter-security")
    implementation("com.auth0", "java-jwt", Versions.jwt)

    implementation("org.springframework.boot", "spring-boot-starter-web")

    implementation("org.springframework.boot", "spring-boot-starter-data-mongodb")

    implementation("org.springframework.kafka", "spring-kafka", Versions.spring_kafka)
    implementation("org.apache.avro", "avro", Versions.avro)
    implementation("io.confluent", "kafka-avro-serializer", Versions.kafka_avro_serializer)

    testImplementation("org.testcontainers", "junit-jupiter")
    testImplementation("org.testcontainers", "mongodb", Versions.test_containers)
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
    testImplementation("org.springframework.boot", "spring-boot-testcontainers")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

avroGenerator {
    schemaDir = file("${projectDir}/../apis/avro")
}
