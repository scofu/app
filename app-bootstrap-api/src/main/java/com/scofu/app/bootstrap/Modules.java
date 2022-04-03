package com.scofu.app.bootstrap;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.reflections.scanners.Scanners.TypesAnnotated;

import com.google.common.io.Resources;
import com.google.inject.Module;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.stream.Stream;
import org.reflections.Reflections;

/**
 * Module utility.
 */
public class Modules {

  private Modules() {
  }

  /**
   * Returns a stream of all modules annotated with
   * {@link com.scofu.common.inject.annotation.Module}.
   *
   * @param classLoader the class loader
   */
  public static Stream<Module> lookupAnnotated(ClassLoader classLoader) {
    final var reflections = new Reflections("com.scofu");
    final var moduleAnnotation = com.scofu.common.inject.annotation.Module.class;
    return reflections.get(TypesAnnotated.with(moduleAnnotation).asClass(classLoader))
        .stream()
        .filter(Module.class::isAssignableFrom)
        .map(Modules::constructModule);
  }

  /**
   * Parses and returns a stream of all modules defined in the '_modules' file/resource.
   *
   * @param classLoader the class loader
   */
  @SuppressWarnings("UnstableApiUsage")
  public static Stream<Module> lookup(ClassLoader classLoader) {
    checkNotNull(classLoader, "classLoader");
    final var file = new File("_modules");
    if (!file.exists()) {
      final var resource = classLoader.getResource("_modules");
      if (resource == null) {
        return Stream.empty();
      }
      try {
        return readModules(Resources.readLines(resource, StandardCharsets.UTF_8).stream());
      } catch (IOException e) {
        e.printStackTrace();
        return Stream.empty();
      }
    }

    try {
      System.out.println("Lines: " + Files.readAllLines(file.toPath()));
      return readModules(Files.lines(file.toPath()));
    } catch (IOException e) {
      e.printStackTrace();
      return Stream.empty();
    }
  }

  private static Stream<Module> readModules(Stream<String> stream) {
    return stream.map(Modules::constructModuleByClassName)
        .filter(Objects::nonNull);
  }

  private static Module constructModuleByClassName(String name) {
    if (name.isEmpty() || name.isBlank()) {
      System.out.println("Empty/blank module! - (" + name + ")");
      return null;
    }
    try {
      System.out.println("name = (" + name + ")");
      final var type = Class.forName(name);
      return constructModule(type);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private static Module constructModule(Class<?> type) {
    try {
      final var constructors = type.getConstructors()[0];
      return (Module) constructors.newInstance();
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }
}
