package com.okta.developer.demo.customtrace;

import org.springframework.boot.actuate.web.trace.servlet.HttpTraceFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@ConditionalOnProperty(prefix = "management.trace.http", name = "enabled", matchIfMissing = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    
    protected HttpTraceFilter httpTraceFilter;

    protected ContentTraceFilter contentTraceFilter;
    
    protected PrincipalTraceFilter principalTraceFilter;
    

    public WebSecurityConfig(HttpTraceFilter httpTraceFilter,
            ContentTraceFilter contentTraceFilter,
            PrincipalTraceFilter principalTraceFilter) {
        super();
        this.httpTraceFilter = httpTraceFilter;
        this.contentTraceFilter = contentTraceFilter;
        this.principalTraceFilter = principalTraceFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(contentTraceFilter,
                SecurityContextPersistenceFilter.class)
                .addFilterAfter(httpTraceFilter,
                        SecurityContextPersistenceFilter.class)
                .addFilterAfter(principalTraceFilter,
                        FilterSecurityInterceptor.class)
                .authorizeRequests().anyRequest().authenticated()
                .and().oauth2Client()
                .and().oauth2Login();
    }
}