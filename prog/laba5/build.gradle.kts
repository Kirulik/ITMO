plugins {
    id("java")
    id("application")
    kotlin("jvm") version "1.9.21"
    java
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.11.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("org.langel.Main")
}

group = "org.langel"
version = "1.0-SNAPSHOT"


// Настраиваем стандартную задачу `jar`
tasks.jar {
    // Меняем базовое имя архива
    archiveBaseName.set("my-super-app")

    // Добавляем атрибуты в манифест JAR-файла
    manifest {
        attributes(
            "Main-Class" to "org.langel.Main", // Указываем главный класс для запуска
            "Implementation-Title" to "My Awesome App",
            "Implementation-Version" to archiveVersion
        )
    }
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

tasks.test {
    useJUnitPlatform()
}

// Настраиваем задачу shadowJar
//tasks.shadowJar {
//    // Указываем, что этот JAR должен быть исполняемым
//    archiveClassifier.set("executable") // Добавит суффикс, например, my-app-1.0-executable.jar
//
//    // Вшиваем аргументы командной строки!
//    args("collection.json", "--default-mode") // Можно передать несколько аргументов
//}