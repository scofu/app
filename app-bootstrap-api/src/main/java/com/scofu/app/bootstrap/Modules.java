package com.scofu.app.bootstrap;

import com.google.common.io.Resources;
import com.google.inject.Module;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Module utility.
 */
public class Modules {

  private Modules() {
  }

  /**
   * Parses and returns a stream of all modules defined in the '_modules' file/resource.
   *
   * @param classLoader the class loader
   * @throws IOException file reading exception
   */
  @SuppressWarnings("UnstableApiUsage")
  public static Stream<Module> lookup(ClassLoader classLoader) throws IOException {
    var file = new File("_modules");
    if (!file.exists()) {
      final var resource = classLoader.getResource("_modules");
      if (resource == null) {
        return Stream.of();
      }
      return readModules(Resources.readLines(resource, StandardCharsets.UTF_8).stream());
    }

    System.out.println("Lines: " + Files.readAllLines(file.toPath()));

    return readModules(Files.lines(file.toPath()));
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
      final var constructors = type.getConstructors()[0];
      return (Module) constructors.newInstance();
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }
}
