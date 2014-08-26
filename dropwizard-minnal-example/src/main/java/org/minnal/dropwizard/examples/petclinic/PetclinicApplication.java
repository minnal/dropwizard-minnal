/**
 * 
 */
package org.minnal.dropwizard.examples.petclinic;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.minnal.dropwizard.MinnalBundle;

/**
 * @author ganeshs
 *
 */
public class PetclinicApplication extends Application<PetclinicConfiguration> {

	@Override
	public void initialize(Bootstrap<PetclinicConfiguration> bootstrap) {
		bootstrap.addBundle(new MinnalBundle(new String[] {"org.minnal.dropwizard.examples.petclinic"}));
	}

	@Override
	public void run(PetclinicConfiguration configuration, Environment environment) throws Exception {
	}

	public static void main(String[] args) throws Exception {
		 new PetclinicApplication().run(args);
	}
}
