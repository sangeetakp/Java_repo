package com.example.demo;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//(exclude = { SecurityAutoConfiguration.class })
//@EnableWebSecurity
public class DemoApplication {

	public static void main(String[] args) throws Exception, LifecycleException {
		SpringApplication.run(DemoApplication.class, args);
	}

	// for jobss eap ?
	@Bean
	public ServletWebServerFactory servletContainer() {
		// enable ssl traffic
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint constraint = new SecurityConstraint();
				constraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				constraint.addCollection(collection);
				context.addConstraint(constraint);
			}
		};
		// add http to https redirect
		tomcat.addAdditionalTomcatConnectors(httpTohttpsRedirectConnector());
		return tomcat;
	}

	private Connector httpTohttpsRedirectConnector() {
		Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		connector.setScheme("http");
		connector.setPort(8082);
		connector.setSecure(false);
		connector.setRedirectPort(8443);
		return connector;
	}

}
