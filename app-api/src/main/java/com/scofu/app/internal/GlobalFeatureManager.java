package com.scofu.app.internal;

import com.scofu.app.annotation.IgnoreGlobalFeatureManager;
import com.scofu.common.inject.AbstractFeatureManager;
import com.scofu.common.inject.Feature;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A global feature manager.
 */
public class GlobalFeatureManager extends AbstractFeatureManager {

  @Override
  protected void load() {
    manageStage(Feature::load, IgnoreGlobalFeatureManager::load);
  }

  @Override
  protected void enable() {
    manageStage(Feature::enable, IgnoreGlobalFeatureManager::enable);
  }

  @Override
  protected void disable() {
    manageStage(Feature::disable, IgnoreGlobalFeatureManager::disable);
  }

  private void manageStage(Consumer<? super Feature> stageInvoker,
      Function<? super IgnoreGlobalFeatureManager, Boolean> stageExtractor) {
    for (var feature : this) {
      if (doesFeatureIgnoreStage(feature, stageExtractor)) {
        return;
      }
      stageInvoker.accept(feature);
    }
  }

  private Boolean doesFeatureIgnoreStage(Feature feature,
      Function<? super IgnoreGlobalFeatureManager, Boolean> stageExtractor) {
    return Optional.ofNullable(feature.getClass().getAnnotation(IgnoreGlobalFeatureManager.class))
        .map(stageExtractor).orElse(false);
  }
}
