package com.scofu.app.bootstrap;

import static com.google.inject.name.Names.named;

import com.scofu.common.inject.AbstractFeatureModule;

/**
 * Binds @Named("Animal") String to "Cat".
 */
public class TestModule extends AbstractFeatureModule {

  @Override
  protected void configure() {
    bind(String.class).annotatedWith(named("Animal")).toInstance("Cat");
  }
}
