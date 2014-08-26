/**
 * 
 */
package org.minnal.dropwizard;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNotNull;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.activejpa.enhancer.ActiveJpaAgentLoader;
import org.glassfish.jersey.server.ResourceConfig;
import org.minnal.instrument.filter.ResponseTransformationFilter;
import org.minnal.instrument.util.MinnalModule;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ganeshs
 *
 */
public class MinnalBundleTest {

	private MinnalBundle bundle;
	
	@BeforeMethod
	public void setup() {
		bundle = spy(new MinnalBundle(new String[] {"org.minnal.dropwizard"}));
	}
	
	@Test
	public void shouldLoadActiveJpaOnInitialize() {
		Bootstrap bootstrap = mock(Bootstrap.class);
		ActiveJpaAgentLoader loader = mock(ActiveJpaAgentLoader.class);
		doReturn(loader).when(bundle).getActiveJpaAgentLoader();
		bundle.initialize(bootstrap);
		verify(loader).loadAgent();
	}
	
	@Test
	public void shouldRegisterComponentsOnRun() {
		JerseyEnvironment jersey = mock(JerseyEnvironment.class);
		Environment environment = mock(Environment.class);
		ResourceConfig config = mock(ResourceConfig.class);
		ObjectMapper mapper = mock(ObjectMapper.class);
		when(environment.jersey()).thenReturn(jersey);
		when(jersey.getResourceConfig()).thenReturn(config);
		when(environment.getObjectMapper()).thenReturn(mapper);
		
		DropwizardApplicationEnhancer enhancer = mock(DropwizardApplicationEnhancer.class);
		doReturn(enhancer).when(bundle).createApplicationEnhancer(environment);
		bundle.run(environment);
		verify(config).register(any(ResponseTransformationFilter.class));
		verify(mapper).registerModule(any(MinnalModule.class));
		verify(enhancer).enhance();
	}
	
	@Test
	public void shouldCreateApplicationEnhancer() {
		JerseyEnvironment jersey = mock(JerseyEnvironment.class);
		Environment environment = mock(Environment.class);
		ResourceConfig config = mock(ResourceConfig.class);
		when(environment.jersey()).thenReturn(jersey);
		when(jersey.getResourceConfig()).thenReturn(config);
		assertNotNull(bundle.createApplicationEnhancer(environment));
	}
}
