package com.scofu.app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.inject.Stage;
import org.junit.jupiter.api.Test;

/** Tests {@link Service}. */
public class ServiceTest {

  @Test
  public void testServiceLoad() {
    final var service =
        new Service() {
          boolean loaded = false;

          @Override
          public void load() {
            loaded = true;
          }
        };
    assertFalse(service.loaded);
    Service.load(Stage.PRODUCTION, service);
    assertTrue(service.loaded);
  }
}
