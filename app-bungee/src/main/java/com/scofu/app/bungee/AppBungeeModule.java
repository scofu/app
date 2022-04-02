package com.scofu.app.bungee;

import com.google.inject.Scopes;
import com.scofu.common.inject.AbstractFeatureModule;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.PluginManager;

/**
 * Binds common Bungee interfaces.
 */
public class AppBungeeModule extends AbstractFeatureModule {

  @Override
  protected void configure() {
    bind(ProxyServer.class).toInstance(ProxyServer.getInstance());
    bind(PluginManager.class).toInstance(ProxyServer.getInstance().getPluginManager());
    bindFeatureManager(ListenerManager.class).in(Scopes.SINGLETON);
  }
}
