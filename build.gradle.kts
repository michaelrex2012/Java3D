plugins {
    application
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.main"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.formdev:flatlaf:3.6")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("DemoViewer")
}

tasks {
    shadowJar {
        archiveBaseName.set("Java3D")
        archiveClassifier.set("")
        archiveVersion.set("0.4")
        mergeServiceFiles()
    }

    build {
        dependsOn(shadowJar)
    }
}