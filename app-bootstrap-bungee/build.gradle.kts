plugins {
    id("bungee-conventions")
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