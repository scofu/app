plugins {
    id("com.scofu.common-build.bungee") version "1.0-SNAPSHOT"
}

dependencies {
    implementation(project(":app-bungee"))
    implementation(project(":app-bootstrap-api"))
}

app {
    this.mainClass.set("com.scofu.app.bootstrap.bungee.AppBootstrapPlugin")
}

bungee {
    main="com.scofu.app.bootstrap.bungee.AppBootstrapPlugin"
}