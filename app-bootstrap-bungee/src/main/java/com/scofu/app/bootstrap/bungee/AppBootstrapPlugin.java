package com.scofu.app.bootstrap.bungee;

import com.scofu.app.bootstrap.BootstrapModule;
import com.scofu.app.bungee.BungeePlugin;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * Bootstraps all other modules and binds {@link Plugin}.
 */
public class AppBootstrapPlugin extends BungeePlugin {

  @Override
  protected void configure() {
    install(new BootstrapModule(getClass().getClassLoader()));
    bind(Plugin.class).toInstance(this);
  }
}
