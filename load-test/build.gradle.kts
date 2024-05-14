plugins {
    id("java-library")
    id("io.gatling.gradle") version "3.11.2"
}

group = "com.jeferro"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation("io.gatling", "gatling-app", "3.11.2")
    implementation("io.gatling.highcharts", "gatling-charts-highcharts", "3.11.2")
}
