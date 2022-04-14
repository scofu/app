package com.scofu.app.bootstrap;

import com.scofu.common.inject.AbstractFeatureModule;
import java.util.stream.Collectors;

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
    final var modules = Modules.lookupAnnotated(classLoader).toList();
    System.out.println("Modules: " + modules.stream()
        .map(module -> module.getClass().getName())
        .collect(Collectors.joining(", ")));
    modules.forEach(this::install);
    System.out.println("Installed modules!");
  }
}
