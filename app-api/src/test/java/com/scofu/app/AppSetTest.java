package com.scofu.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.scofu.common.inject.AbstractFeatureModule;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Tests {@link AppSet}.
 */
public class AppSetTest {

  @Test
  public void testSize() {
    final var app = new EmptyApp();
    final var appSet = AppSet.resolved(app);
    assertEquals(appSet.size(), 1);
  }

  @Test
  public void testContainsOnlyApp() {
    final var app = new EmptyApp();
    final var appSet = AppSet.resolved(app);
    final var iterator = appSet.iterator();
    assertTrue(iterator.hasNext());
    assertSame(iterator.next(), app);
    assertFalse(iterator.hasNext());
  }

  @Test
  public void testUnresolvedSize() {
    final var app = new EmptyApp();
    final var appSet = AppSet.unresolved(() -> List.of(app, 1, "test", false));
    assertEquals(appSet.size(), 1);
  }

  @Test
  public void testUnresolvedContainsOnlyApp() {
    final var app = new EmptyApp();
    final var appSet = AppSet.unresolved(() -> List.of(app, 1, "test", false));
    final var iterator = appSet.iterator();
    assertTrue(iterator.hasNext());
    assertSame(iterator.next(), app);
    assertFalse(iterator.hasNext());
  }

  static class EmptyApp extends AbstractFeatureModule implements App {}

}
