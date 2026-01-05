plugins {
    kotlin("jvm") version "2.2.20"
    jacoco
}

group = "com.sol.turnera.backend"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val kotestVersion = "5.8.0"

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Configuramos jacocoTestReport usando Kotlin DSL correctamente
tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.test)

    reports {
        xml.required.set(true)
        csv.required.set(true)
        html.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }

    // Excluir paquetes que no queremos cubrir (opcional)
    classDirectories.setFrom(
        files(classDirectories.files.map {
            fileTree(it) {
                exclude("**/config/**", "**/entity/**", "**/Application*.*")
            }
        })
    )
    executionData.setFrom(fileTree(buildDir).include("/jacoco/test.exec"))
}

// Task personalizado para GitHub Actions
tasks.register("runOnGitHub") {
    dependsOn("jacocoTestReport")
    group = "custom"
    description = "$ ./gradlew runOnGitHub # runs on GitHub Action"
}