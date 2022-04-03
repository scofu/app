package com.scofu.app.bootstrap;

import com.scofu.common.inject.AbstractFeatureModule;

/**
 * Looks up all modules and installs them.
 *
 * <p>See {@link Modules#lookupAnnotated(ClassLoader)}.
 */
public class BootstrapModule extends AbstractFeatureModule {

  private final ClassLoader classLoader;

  public BootstrapModule(ClassLoader classLoader) {
    this.classLoader = classLoader;
  }

  @Override
  protected void configure() {
    Modules.lookupAnnotated(classLoader).forEach(this::install);
  }
}
