plugins {
    kotlin("jvm")
    application
}

dependencies {
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("ru.dimsuz.skif.MainKt")
}
