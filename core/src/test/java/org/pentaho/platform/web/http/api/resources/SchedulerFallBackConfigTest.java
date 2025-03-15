package org.pentaho.platform.web.http.api.resources;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.pentaho.platform.api.engine.IPluginResourceLoader;
import org.pentaho.platform.engine.core.system.PentahoSystem;

public class SchedulerFallBackConfigTest {

    private IPluginResourceLoader resourceLoader;
    private MockedStatic<PentahoSystem> mockedPentahoSystem;

    private SchedulerFallBackConfig config;

    @Before
    public void setupMocks() {
        mockedPentahoSystem = Mockito.mockStatic(PentahoSystem.class);
        resourceLoader = mock(IPluginResourceLoader.class);
        mockedPentahoSystem.when(() -> PentahoSystem.get(IPluginResourceLoader.class, null)).thenReturn(resourceLoader);
        config = SchedulerFallBackConfig.getInstance();
    }

    @After
    public void closeMocks() {
        mockedPentahoSystem.close();
    }

    @Test
    public void testIsFallbackEnabledTrue() {
        when(resourceLoader.getPluginSetting(SchedulerFallBackConfig.class, "settings/scheduler-fallback", "false")).thenReturn("true");
        config.resetFallback();
        assertTrue( config.isFallbackEnabled() );
    }

    @Test
    public void testIsFallbackEnabledFalse() {
        when(resourceLoader.getPluginSetting(SchedulerFallBackConfig.class, "settings/scheduler-fallback", "false")).thenReturn("false");
        config.resetFallback();
        assertFalse(config.isFallbackEnabled());
    }
}
