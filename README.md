dropwizard-minnal
=================

A Dropwizard bundle that enhances the entity classes using Minnal instrumentation module and auto generates the REST APIs.


Getting Started
---------------
### Setting Up Maven

Include the following maven dependencies in your dropwizard application,

```xml
   <dependencies>
     <dependency>
  		<groupId>org.minnal</groupId>
  		<artifactId>dropwizard-minnal</artifactId>
  		<version>0.0.1</version>
  	</dependency>
   </dependencies>
   
   <repositories>
    <repository>
      <id>activejpa-repo</id>
      <url>https://raw.github.com/ActiveJpa/activejpa/mvn-repo/releases</url>
    </repository>
    <repository>
      <id>autopojo-repo</id>
      <url>https://raw.github.com/minnal/mvn-repo/master/releases</url>
    </repository>
    <repository>
      <id>minnal-repo</id>
      <url>https://raw.github.com/minnal/mvn-repo/master/releases</url>
    </repository>
  </repositories>
```

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

