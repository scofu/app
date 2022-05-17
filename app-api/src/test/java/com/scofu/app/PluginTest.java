package com.scofu.app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.inject.Stage;
import com.scofu.app.internal.AppLoader;
import com.scofu.common.inject.AbstractFeatureModule;
import org.junit.jupiter.api.Test;

/** Tests {@link Plugin}. */
public class PluginTest {

  @Test
  public void testOnlyLoadWhenReady() {
    abstract class AbstractPlugin extends AbstractFeatureModule implements Plugin {

      boolean loaded = false;

      @Override
      public void load() {
        loaded = true;
      }
    }

    final var notAlwaysReadyPlugin =
        new AbstractPlugin() {
          boolean ready = false;

          @Override
          public boolean isReadyToLoad() {
            return ready;
          }
        };

    final var alwaysReadyPlugin =
        new AbstractPlugin() {
          @Override
          public boolean isReadyToLoad() {
            return true;
          }
        };

    final var plugins = AppSet.resolved(notAlwaysReadyPlugin, alwaysReadyPlugin);
    final var pluginLoader = AppLoader.newAppLoader(Stage.PRODUCTION, plugins);
    assertFalse(notAlwaysReadyPlugin.loaded);
    assertFalse(alwaysReadyPlugin.loaded);

    pluginLoader.load();
    assertFalse(notAlwaysReadyPlugin.loaded);
    assertFalse(alwaysReadyPlugin.loaded);

    notAlwaysReadyPlugin.ready = true;
    pluginLoader.load();
    assertTrue(notAlwaysReadyPlugin.loaded);
    assertTrue(alwaysReadyPlugin.loaded);
  }
}
