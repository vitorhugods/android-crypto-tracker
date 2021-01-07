version = LibraryKotlinCoordinates.LIBRARY_VERSION

plugins {
    id("java-library")
    kotlin("jvm")
    kotlin("plugin.serialization") version BuildPluginsVersion.KOTLIN
    id("maven-publish")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation(LibCoroutines.CORE)
    implementation(LibCoroutines.ANDROID)

    testImplementation(LibTesting.JUNIT)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
