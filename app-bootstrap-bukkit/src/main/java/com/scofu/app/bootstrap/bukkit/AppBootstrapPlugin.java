package com.scofu.app.bootstrap.bukkit;

import com.scofu.app.bootstrap.BootstrapModule;
import com.scofu.app.bukkit.BukkitPlugin;
import org.bukkit.plugin.Plugin;

/** Bootstraps all other modules and binds {@link Plugin}. */
public class AppBootstrapPlugin extends BukkitPlugin {

  @Override
  protected void configure() {
    install(new BootstrapModule(getClassLoader()));
    bind(Plugin.class).toInstance(this);
  }
}
