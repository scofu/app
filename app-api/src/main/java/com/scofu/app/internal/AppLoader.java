package com.scofu.app.internal;

import com.google.inject.Guice;
import com.google.inject.Stage;
import com.scofu.app.App;
import com.scofu.app.AppSet;
import com.scofu.app.StatefulApp;
import com.scofu.common.inject.FeatureBootstrap;

/**
 * Loads applications.
 *
 * @param <A> the type of the applications
 */
public class AppLoader<A extends App> {

  private final Stage stage;
  private final AppSet<A> appSet;

  private AppLoader(Stage stage, AppSet<A> appSet) {
    this.stage = stage;
    this.appSet = appSet;
  }

  /**
   * Creates and returns a new app loader.
   *
   * @param stage  the stage
   * @param appSet the app set
   * @param <A>    the type of the apps
   */
  public static <A extends App> AppLoader<A> newAppLoader(Stage stage, AppSet<A> appSet) {
    return new AppLoader<>(stage, appSet);
  }

  /**
   * Attempts to load all the apps.
   */
  public void load() {
    final var readySize = (int) appSet.stream().filter(
            app -> !(app instanceof StatefulApp statefulApp)
                || statefulApp.isReadyToLoad())
        .count();
    if (readySize != appSet.size()) {
      return;
    }
    try {
      final var injector = Guice.createInjector(stage, new AppModule<>(appSet));
      final var featureBootstrap = injector.getInstance(FeatureBootstrap.class);
      featureBootstrap.load();
      featureBootstrap.enable();
      for (var app : appSet) {
        injector.injectMembers(app);
        app.inject(injector);
      }
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }
}
