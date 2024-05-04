import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.jeferro.plugins.avro-generator")
}

dependencies {
    implementation("org.springframework.boot", "spring-boot-starter")
    api("org.springframework.kafka", "spring-kafka", Versions.spring_kafka)

    api("org.apache.avro", "avro", Versions.avro)
    implementation("io.confluent", "kafka-avro-serializer", Versions.kafka_avro_serializer)
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

avroGenerator {
    schemaDir = file("${projectDir}/src/main/avro")
    targetDir = file("${projectDir}/build/generated/sources/avro")
}
