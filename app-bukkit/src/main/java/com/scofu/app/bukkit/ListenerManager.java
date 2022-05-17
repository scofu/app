package com.scofu.app.bukkit;

import com.google.inject.Inject;
import com.scofu.common.inject.AbstractFeatureManager;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

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
        .forEach(
            listener -> {
              System.out.println("Registering listener: " + listener);
              pluginManager.registerEvents(listener, plugin);
            });
  }

  @Override
  protected void disable() {
    streamWithType(Listener.class).forEach(HandlerList::unregisterAll);
  }
}
