import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.71"
    application
}

apply {
    plugin("idea")
    plugin("java")
    plugin("kotlin")
    plugin("application")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    // https://mvnrepository.com/artifact/org.antlr/antlr4-runtime
    compile("org.antlr:antlr4-runtime:4.8-1")
}

repositories {
    mavenCentral()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

sourceSets["main"].java.srcDir("src/")

application {
    mainClassName = "MainKt"
}