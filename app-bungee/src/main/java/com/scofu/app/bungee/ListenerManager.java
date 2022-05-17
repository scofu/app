package com.scofu.app.bungee;

import com.google.inject.Inject;
import com.scofu.common.inject.AbstractFeatureManager;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

/** Manages features that also implement {@link Listener}. */
public class ListenerManager extends AbstractFeatureManager {

  private final Plugin plugin;
  private final PluginManager pluginManager;

  @Inject
  ListenerManager(Plugin plugin, PluginManager pluginManager) {
    this.plugin = plugin;
    this.pluginManager = pluginManager;
  }

  @Override
  protected void load() {
    streamWithType(Listener.class)
        .forEach(listener -> pluginManager.registerListener(plugin, listener));
  }

  @Override
  protected void disable() {
    streamWithType(Listener.class).forEach(pluginManager::unregisterListener);
  }
}
