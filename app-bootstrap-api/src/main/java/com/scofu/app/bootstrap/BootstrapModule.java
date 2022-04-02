package com.scofu.app.bootstrap;

import com.scofu.common.inject.AbstractFeatureModule;
import java.io.IOException;

/**
 * Looks up all modules in a class loader and installs them.
 */
public class BootstrapModule extends AbstractFeatureModule {

  private final ClassLoader classLoader;

  public BootstrapModule(ClassLoader classLoader) {
    this.classLoader = classLoader;
  }

  @Override
  protected void configure() {
    try {
      Modules.lookup(classLoader).forEach(this::install);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
