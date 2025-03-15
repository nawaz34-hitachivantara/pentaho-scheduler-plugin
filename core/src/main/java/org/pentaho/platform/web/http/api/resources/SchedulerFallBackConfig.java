/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 by Hitachi Vantara, LLC : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2029-07-20
 ******************************************************************************/

package org.pentaho.platform.web.http.api.resources;

import org.pentaho.platform.api.engine.IPluginResourceLoader;
import org.pentaho.platform.engine.core.system.PentahoSystem;

/**
 * Configuration class for the Scheduler fallback mechanism.
 * This class loads the fallback setting from the plugin configuration
 * and provides a method to check if the fallback is enabled.
 */
public class SchedulerFallBackConfig {
  private static final SchedulerFallBackConfig instance = new SchedulerFallBackConfig();

    /**
     * Indicates whether the fallback mechanism is enabled.
     * This value is loaded from the plugin settings during class initialization.
     */
  private boolean fallback;

  private SchedulerFallBackConfig() {
    this.fallback = initializeFallback();
  }

  /**
  * Initializes the fallback variable by loading the setting from the plugin configuration.
  *
  * @return {@code true} if the fallback mechanism is enabled, {@code false} otherwise.
  */
  private boolean initializeFallback() {
    IPluginResourceLoader resourceLoader = PentahoSystem.get( IPluginResourceLoader.class, null );
    String fallbackSetting = resourceLoader.getPluginSetting( SchedulerFallBackConfig.class, "settings/scheduler-fallback", "false" );
    return Boolean.parseBoolean( fallbackSetting );
  }

  /**
  * Gets the singleton instance of the SchedulerFallBackConfig.
  *
  * @return The singleton instance.
  */
  public static SchedulerFallBackConfig getInstance() {
    return instance;
  }

  /**
  * Checks if the fallback mechanism for the scheduler is enabled.
  *
  * @return {@code true} if the fallback mechanism is enabled, {@code false} otherwise.
  */
  public boolean isFallbackEnabled() {
    return fallback;
  }

  /**
  * Resets the fallback value by reinitializing it.
  */
  public void resetFallback() {
    this.fallback = initializeFallback();
  }
}
