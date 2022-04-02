package com.scofu.app.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Features annotated with this can ignore stages to be managed from the {@link
 * com.scofu.app.internal.GlobalFeatureManager}.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreGlobalFeatureManager {

  /**
   * Whether to ignore the 'load' stage or not.
   */
  boolean load() default true;

  /**
   * Whether to ignore the 'enable' stage or not.
   */
  boolean enable() default true;

  /**
   * Whether to ignore the 'disable' stage or not.
   */
  boolean disable() default true;
}
