package com.scofu.app.bootstrap;

import com.scofu.common.inject.AbstractFeatureModule;

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
    Modules.lookup(classLoader).forEach(this::install);
  }
}
