package com.scofu.app.internal;

import com.google.inject.Scopes;
import com.scofu.app.App;
import com.scofu.app.AppSet;
import com.scofu.common.inject.AbstractFeatureModule;

/**
 * Installs a set of apps.
 *
 * @param <A> the type of the apps
 */
public class AppModule<A extends App> extends AbstractFeatureModule {

  private final AppSet<A> appSet;

  public AppModule(AppSet<A> appSet) {
    this.appSet = appSet;
  }

  @Override
  protected void configure() {
    for (var app : appSet) {
      install(app);
      bindFeatureInstance(app);
    }
    bindFeatureManager(GlobalFeatureManager.class).in(Scopes.SINGLETON);
  }
}
