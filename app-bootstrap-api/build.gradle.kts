plugins {
    id("com.scofu.common-build.base") version "1.0-SNAPSHOT"
}

dependencies {
    api(project(":app-api"))
    implementation("org.reflections:reflections:0.10.2")
}

app {
    shadowFirstLevel()
}