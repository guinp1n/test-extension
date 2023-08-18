plugins {
    alias(libs.plugins.hivemq.enterpriseExtension)
    alias(libs.plugins.defaults)
}

group = "com.hivemq.extensions"
description = "HiveMQ 4 Hello World Enterprise Extension - a simple reference for all enterprise extension developers"

hivemqExtension {
    name.set("Hello World Enterprise Extension")
    author.set("HiveMQ")
    priority.set(1000)
    startPriority.set(1000)
    mainClass.set("$group.helloworld.HelloWorldEnterpriseMain")
    sdkVersion.set("$version")
}

dependencies {
    implementation(libs.commons.lang3)
}

/* ******************** test ******************** */

dependencies {
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockito)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

/* ******************** integration test ******************** */

dependencies {
    integrationTestImplementation(libs.hivemq.mqttClient)
    integrationTestImplementation(libs.testcontainers.junitJupiter)
    integrationTestImplementation(libs.testcontainers.hivemq)
    integrationTestRuntimeOnly(libs.logback.classic)
}

/* ******************** debugging ******************** */

tasks.prepareHivemqHome {
    hivemqHomeDirectory.set(file("/your/path/to/hivemq-<VERSION>"))
}

tasks.runHivemqWithExtension {
    debugOptions {
        enabled.set(false)
    }
}
