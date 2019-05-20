package com.okta.developer.demo;


import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
@ConditionalOnProperty(prefix = "management.trace.http", name="enabled", havingValue="false")
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	.requestMatcher(EndpointRequest.toAnyEndpoint())
    	.authorizeRequests()
		.anyRequest().permitAll();
    }
}