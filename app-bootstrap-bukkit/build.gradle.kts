plugins {
    id("com.scofu.common-build.bukkit") version "1.0-SNAPSHOT"
}

dependencies {
    implementation(project(":app-bukkit"))
    implementation(project(":app-bootstrap-api"))
}

app {
    this.mainClass.set("com.scofu.app.bootstrap.bukkit.AppBootstrapPlugin")
}

bukkit {
    main="com.scofu.app.bootstrap.bukkit.AppBootstrapPlugin"
}