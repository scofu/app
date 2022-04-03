package com.scofu.app.bootstrap;

import static com.google.inject.name.Names.named;

import com.scofu.common.inject.AbstractFeatureModule;
import com.scofu.common.inject.annotation.Module;

/**
 * Binds @Named("Animal") String to "Cat".
 */
@Module
public class TestModule extends AbstractFeatureModule {

  @Override
  protected void configure() {
    bind(String.class).annotatedWith(named("Animal")).toInstance("Cat");
  }
}
