plugins {
    id("paper-conventions")
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