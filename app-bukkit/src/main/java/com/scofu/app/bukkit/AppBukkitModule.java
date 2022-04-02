package com.scofu.app.bukkit;

import com.google.inject.Scopes;
import com.scofu.common.inject.AbstractFeatureModule;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;

/**
 * Binds common Bukkit interfaces.
 */
public class AppBukkitModule extends AbstractFeatureModule {

  @Override
  protected void configure() {
    bind(Server.class).toInstance(Bukkit.getServer());
    bind(PluginManager.class).toInstance(Bukkit.getPluginManager());
    bindFeatureManager(ListenerManager.class).in(Scopes.SINGLETON);
  }
}
