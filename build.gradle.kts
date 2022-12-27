import com.google.protobuf.gradle.*
plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    id("org.jetbrains.kotlin.kapt") version "1.6.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.21"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.6.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.micronaut.application") version "3.6.3"
    id("com.google.protobuf") version "0.8.15"
}

version = "0.1"
group = "com.angularlabs"

val kotlinVersion= project.properties["kotlinVersion"]
repositories {
    mavenCentral()
}

dependencies {
    kapt("io.micronaut.data:micronaut-data-processor")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
    implementation("io.micronaut.grpc:micronaut-grpc-runtime")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.h2database:h2")
    implementation("io.micronaut:micronaut-validation")
    implementation("io.micronaut:micronaut-http-client")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("io.micronaut:micronaut-http-client")
    testImplementation("org.mockito:mockito-core:4.9.0")


}


application {
    mainClass.set("com.angularlabs.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("18")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "18"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "18"
        }
    }
}
sourceSets {
    main {
        java {
            srcDirs("build/generated/source/proto/main/grpc")
            srcDirs("build/generated/source/proto/main/java")
        }
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.20.1"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.46.0"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                // Apply the "grpc" plugin whose spec is defined above, without options.
                id("grpc")
            }
        }
    }
}
micronaut {
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.angularlabs.*")
    }
}



