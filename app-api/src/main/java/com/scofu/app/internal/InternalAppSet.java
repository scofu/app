package com.scofu.app.internal;

import com.scofu.app.App;
import com.scofu.app.AppSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Internal app set.
 *
 * @param <A> the type of the apps
 */
@SuppressWarnings("ClassCanBeRecord")
public final class InternalAppSet<A extends App> implements AppSet<A> {

  private final Supplier<Collection<?>> unresolved;

  private InternalAppSet(Supplier<Collection<?>> unresolved) {
    this.unresolved = unresolved;
  }

  /**
   * Creates and returns a new internal app set.
   *
   * @param unresolved the unresolved apps
   * @param <A> the type of the apps
   */
  public static <A extends App> InternalAppSet<A> newInternalAppSet(
      Supplier<Collection<?>> unresolved) {
    return new InternalAppSet<A>(unresolved);
  }

  @Override
  public int size() {
    return (int) stream().count();
  }

  @SuppressWarnings("unchecked")
  @Override
  public Stream<A> stream() {
    return unresolved.get().stream().filter(o -> o instanceof App).map(o -> (A) o);
  }

  @Override
  public Iterator<A> iterator() {
    return stream().iterator();
  }
}
