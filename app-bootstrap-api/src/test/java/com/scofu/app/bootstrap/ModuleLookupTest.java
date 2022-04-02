package com.scofu.app.bootstrap;

import static com.google.inject.name.Names.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.inject.Guice;
import com.google.inject.Key;
import org.junit.jupiter.api.Test;

/**
 * Tests module lookup.
 */
public class ModuleLookupTest {

  @Test
  public void testLookup() {
    final var injector = Guice.createInjector(
        new BootstrapModule(getClass().getClassLoader()));
    final var animal = injector.getInstance(Key.get(String.class, named("Animal")));
    assertEquals(animal, "Cat");
  }
}
