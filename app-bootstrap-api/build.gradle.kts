plugins {
    id("base-conventions")
}

dependencies {
    api(project(":app-api"))
    implementation("org.reflections:reflections:0.10.2")
}