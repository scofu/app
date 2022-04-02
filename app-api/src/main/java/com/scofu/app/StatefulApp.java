package com.scofu.app;

/**
 * An app that can show whether it is ready to be loaded or not.
 */
public interface StatefulApp extends App {

  /**
   * Called when this app is in the process of being loaded.
   */
  boolean isReadyToLoad();

}
