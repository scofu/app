package com.scofu.app;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.inject.Stage;
import com.scofu.app.internal.AppLoader;
import com.scofu.common.inject.AbstractFeatureModule;

/**
 * Represents a single app service.
 */
public class Service extends AbstractFeatureModule implements App {

  /**
   * Loads the given service.
   *
   * @param stage   the stage
   * @param service the service
   * @param <S>     the type of the service
   */
  public static <S extends Service> void load(Stage stage, S service) {
    checkNotNull(stage, "stage");
    checkNotNull(service, "service");
    final var loader = AppLoader.newAppLoader(stage, AppSet.resolved(service));
    loader.load();
  }

}
