import com.google.cloud.tools.gradle.appengine.appyaml.AppEngineAppYamlExtension

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.23"
    id("io.ktor.plugin") version "2.3.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.23"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("com.google.cloud.tools.appengine") version "2.4.2"
}

group = "akshaw.com"
version = "0.0.1"
val mainClassName = "akshaw.com.ApplicationKt"

application {
    mainClass.set("akshaw.com.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

kotlin {
    jvmToolchain(11)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-default-headers-jvm")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-host-common-jvm")
    implementation("io.ktor:ktor-server-status-pages-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("org.jetbrains.exposed:exposed-core:0.41.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.41.1")
    implementation("com.h2database:h2:2.1.214")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

//tasks.register<Jar>("fatJar") {
//    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
//    manifest {
//        attributes("Main-Class" to mainClassName)
//    }
//    from(configurations.runtimeClasspath.get().map {
//        if (it.isDirectory) it else zipTree(it)
//    })
//    with(tasks.jar.get())
//}

configure<AppEngineAppYamlExtension> {
    stage {
        setArtifact("build/libs/${project.name}-all.jar")
    }
    deploy {
        version = "GCLOUD_CONFIG"
        projectId = "GCLOUD_CONFIG"
    }
}

tasks {
    shadowJar {
        manifest {
            attributes["Main-Class"] = mainClassName
        }
    }
}
