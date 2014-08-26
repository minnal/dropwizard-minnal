dropwizard-minnal
=================

A Dropwizard bundle that enhances the entity classes using [Minnal](https://github.com/minnal/minnal) instrumentation module and auto generates the REST APIs.


Getting Started
---------------
### Setting Up Maven

Include the following maven dependencies in your dropwizard application,

```xml
   <dependencies>
     <dependency>
  		<groupId>org.minnal</groupId>
  		<artifactId>dropwizard-minnal</artifactId>
  		<version>0.0.3</version>
  	</dependency>
   </dependencies>
   
   <repositories>
    <repository>
      <id>activejpa-repo</id>
      <url>https://raw.github.com/ActiveJpa/activejpa/mvn-repo/releases</url>
    </repository>
    <repository>
      <id>minnal-repo</id>
      <url>https://raw.github.com/minnal/mvn-repo/master/releases</url>
    </repository>
  </repositories>
```

NOTE: This version depends on the [dropwizard branch](https://github.com/saadmufti/dropwizard/tree/jersey-2) and [metrics branch](https://github.com/saadmufti/metrics/tree/jersey2-upgrade). You will have to build the snapshot versions from these repos locally to use this module.

### Adding the bundle to your application

```java
public class TestApplication extends Application<TestConfiguration> {

	@Override
	public void initialize(Bootstrap<TestConfiguration> bootstrap) {
		 bootstrap.addBundle(new MinnalBundle(new String[] {"org.minnal.dropwizard.test"}));
	}
	
	@Override
	public void run(TestConfiguration configuration, Environment environment) throws Exception {
	}
}
```

License
-------
ActiveJPA is offered under Apache License, Version 2.0

