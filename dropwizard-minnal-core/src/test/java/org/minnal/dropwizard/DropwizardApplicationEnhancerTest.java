/**
 * 
 */
package org.minnal.dropwizard;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Environment;

import org.glassfish.jersey.server.ResourceConfig;
import org.minnal.instrument.UnderscoreNamingStrategy;
import org.testng.annotations.Test;

/**
 * @author ganeshs
 *
 */
public class DropwizardApplicationEnhancerTest {

	@Test
	public void shouldAddResourceToConfig() {
		JerseyEnvironment jersey = mock(JerseyEnvironment.class);
		Environment environment = mock(Environment.class);
		ResourceConfig config = mock(ResourceConfig.class);
		when(environment.jersey()).thenReturn(jersey);
		when(jersey.getResourceConfig()).thenReturn(config);
		DropwizardApplicationEnhancer enhancer = new DropwizardApplicationEnhancer(environment, new String[] {"org.minnal"}, new UnderscoreNamingStrategy());
		enhancer.addResource(DummyResource.class);
		verify(config).register(DummyResource.class);
	}
	
	private static class DummyResource {
	}
}
