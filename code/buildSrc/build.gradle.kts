plugins {
    java
    `java-gradle-plugin`
}

repositories {
    mavenLocal()
    mavenCentral()
}


dependencies {
    implementation("org.apache.avro", "avro-compiler", "1.11.3")
}

gradlePlugin {
    plugins {
        create("avro-generator") {
            id = "com.jeferro.plugins.avro-generator"
            implementationClass = "com.jeferro.plugins.AvroGeneratorPlugin"
        }
    }
}