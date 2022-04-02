package com.scofu.app;

import com.google.inject.Injector;
import com.scofu.common.inject.Feature;
import com.scofu.common.inject.FeatureModule;

/**
 * App feature.
 */
public interface App extends FeatureModule, Feature {

  /**
   * Called when the entire app set has been configured.
   *
   * @param injector the injector
   */
  default void inject(Injector injector) {
  }

}
