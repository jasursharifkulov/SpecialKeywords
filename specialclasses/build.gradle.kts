plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions { jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11 }
}
for (number in 1..15) {
    val taskNumber = number.toString().padStart(2, '0')
    tasks.register<JavaExec>("runTask$taskNumber") {
        group = "application"
        description = "Run laboratory task $taskNumber"
        classpath = sourceSets["main"].runtimeClasspath
        mainClass.set("task$taskNumber.MainKt")
        standardInput = System.`in`
    }
}
