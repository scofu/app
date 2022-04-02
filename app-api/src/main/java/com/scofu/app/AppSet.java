package com.scofu.app;

import static com.google.common.base.Preconditions.checkNotNull;

import com.scofu.app.internal.InternalAppSet;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Represents a set of apps.
 *
 * @param <A> the type of the apps
 */
public interface AppSet<A extends App> extends Iterable<A> {

  /**
   * Creates and returns a new unresolved set of apps.
   *
   * @param unresolved the unresolved objects that may contain apps
   * @param <A>        the type of the apps
   */
  static <A extends App> AppSet<A> unresolved(Supplier<Collection<?>> unresolved) {
    checkNotNull(unresolved, "unresolved");
    return InternalAppSet.newInternalAppSet(unresolved);
  }

  /**
   * Creates and returns a new resolved set of apps.
   *
   * @param applications the apps
   * @param <A>          the type of the apps
   */
  @SafeVarargs
  static <A extends App> AppSet<A> resolved(A... applications) {
    checkNotNull(applications, "applications");
    final var unresolved = List.of(applications);
    return unresolved(() -> unresolved);
  }

  /**
   * Returns the size.
   */
  int size();

  /**
   * Returns a stream of the apps.
   */
  Stream<A> stream();
}
