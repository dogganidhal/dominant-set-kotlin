plugins {
    kotlin("jvm") version "1.4.10"
}

group = "com.ndogga"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(files("jars/supportGUI.jar"))
    implementation("com.github.lamba92.KGraph:KGraph-jvm:1.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}