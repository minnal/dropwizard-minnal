/**
 * 
 */
package org.minnal.dropwizard;

import io.dropwizard.Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.activejpa.enhancer.ActiveJpaAgentLoader;
import org.glassfish.jersey.server.ResourceConfig;
import org.minnal.instrument.ApplicationEnhancer;
import org.minnal.instrument.DefaultNamingStrategy;
import org.minnal.instrument.filter.ResponseTransformationFilter;
import org.minnal.instrument.util.MinnalModule;

/**
 * Minnal bundle that instruments all the entities defined to auto generate APIs
 * 
 * @author ganeshs
 *
 */
public class MinnalBundle implements Bundle {
	
	private String[] packagesToScan;
	
	/**
	 * @param packagesToScan
	 */
	public MinnalBundle(String[] packagesToScan) {
		this.packagesToScan = packagesToScan;
	}

	public void initialize(Bootstrap<?> bootstrap) {
		getActiveJpaAgentLoader().loadAgent();
	}
	
	protected ActiveJpaAgentLoader getActiveJpaAgentLoader() {
		return ActiveJpaAgentLoader.instance();
	}

	public void run(Environment environment) {
		ResourceConfig config = environment.jersey().getResourceConfig();
		config.register(ResponseTransformationFilter.class);
		environment.getObjectMapper().registerModule(new MinnalModule());
		createApplicationEnhancer(environment).enhance();
	}
	
	protected ApplicationEnhancer createApplicationEnhancer(Environment environment) {
		return new DropwizardApplicationEnhancer(environment, packagesToScan, new DefaultNamingStrategy());
	}

}
