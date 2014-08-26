/**
 *
 */
package org.minnal.dropwizard.test;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import java.lang.reflect.Method;
import java.util.ServiceLoader;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import org.activejpa.jpa.JPA;
import org.minnal.jaxrs.test.BaseJPAResourceTest;
import org.minnal.jaxrs.test.exception.MinnalJaxrsTestException;
import org.minnal.jaxrs.test.provider.JacksonProvider;
import org.testng.annotations.BeforeSuite;

/**
 * @author ganeshs
 */
public abstract class BaseDropwizardResourceTest extends BaseJPAResourceTest {
	
    @SuppressWarnings("rawtypes")
	@BeforeSuite
    public void beforeSuite() {
    	JPA.instance.addPersistenceUnit("test");
    	
    	final Environment[] environments = new Environment[1];

    	final Application origApplication = getApplication();
    	Enhancer enhancer = new Enhancer();
    	enhancer.setSuperclass(origApplication.getClass());
    	enhancer.setCallback(new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if (method.getName().equals("run") && method.getParameterTypes().length == 2 && method.getParameterTypes()[1].equals(Environment.class)) {
					environments[0] = (Environment) args[1];
				}
				return method.invoke(origApplication, args);
			}
		});
    	Application application = (Application) enhancer.create();
    	try {
			application.run(new String[] {"server"});
		} catch (Exception e) {
			throw new MinnalJaxrsTestException("Failed while initializing the dropwizard application - " + application.getName(), e);
		}
        init(environments[0].jersey().getResourceConfig());
        setProvider(new JacksonProvider(environments[0].getObjectMapper()));
    }

    @SuppressWarnings("rawtypes")
    protected Application getApplication() {
    	for (Application application : ServiceLoader.load(Application.class)) {
    		return application;
    	}
    	throw new MinnalJaxrsTestException("Missing application service loader. Please create a file " + Application.class.getCanonicalName()
				+ " under META-INF/services with the fully qualified name of the dropwizard application class in it");
    }
}
