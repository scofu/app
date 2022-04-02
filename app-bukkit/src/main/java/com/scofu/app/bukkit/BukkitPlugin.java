package com.scofu.app.bukkit;

import com.google.common.base.Suppliers;
import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Stage;
import com.scofu.app.AppSet;
import com.scofu.app.Plugin;
import com.scofu.app.internal.AppLoader;
import com.scofu.common.inject.FeatureBinder;
import com.scofu.common.inject.FeatureBootstrap;
import java.util.List;
import java.util.function.Supplier;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Bukkit plugin.
 */
public class BukkitPlugin extends JavaPlugin implements Plugin {

  private static final Supplier<AppLoader<BukkitPlugin>> GLOBAL_LOADER;

  static {
    GLOBAL_LOADER = Suppliers.memoize(() -> AppLoader.newAppLoader(Stage.PRODUCTION,
        AppSet.unresolved(() -> List.of(Bukkit.getPluginManager().getPlugins()))));
  }

  private Binder binder;
  private FeatureBinder featureBinder;
  private boolean readyToLoad;
  @Inject
  private FeatureBootstrap featureBootstrap;

  @Override
  public final void onLoad() {
  }

  @Override
  public final void onEnable() {
    readyToLoad = true;
    GLOBAL_LOADER.get().load();
  }

  @Override
  public final void onDisable() {
    featureBootstrap.disable();
  }

  @Override
  public final boolean isReadyToLoad() {
    return readyToLoad;
  }

  protected void configure() {
  }

  @Override
  public final void configure(Binder binder) {
    try {
      this.binder = binder;
      this.featureBinder = FeatureBinder.newFeatureBinder(binder);
      configure();
    } finally {
      this.binder = null;
      this.featureBinder = null;
    }
  }

  @Override
  public final FeatureBinder featureBinder() {
    return featureBinder;
  }

  @Override
  public final Binder binder() {
    return binder;
  }
}

