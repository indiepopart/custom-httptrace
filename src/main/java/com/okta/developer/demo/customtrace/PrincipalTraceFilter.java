package com.okta.developer.demo.customtrace;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.trace.http.HttpTraceProperties;
import org.springframework.boot.actuate.trace.http.Include;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@ConditionalOnProperty(prefix = "management.trace.http", name="enabled", matchIfMissing=true)
public class PrincipalTraceFilter extends OncePerRequestFilter implements Ordered {
	
	private int order = Ordered.LOWEST_PRECEDENCE;
	
	@Autowired
	protected CustomHttpTraceRepository repository;
	
	@Autowired
	protected ContentTraceManager traceManager;
	
	@Autowired
	protected HttpTraceProperties traceProperties;
	
	
	@Override
	public int getOrder() {
		return order;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!isRequestValid(request)) {
			filterChain.doFilter(request, response);
			return;
		}
		try {
			filterChain.doFilter(request, response);
			
			if (traceProperties.getInclude().contains(Include.PRINCIPAL)) {
				traceManager.updatePrincipal();
			}
		}
		finally {
		}		
		
	}
	
	private boolean isRequestValid(HttpServletRequest request) {
		try {
			new URI(request.getRequestURL().toString());
			return true;
		}
		catch (URISyntaxException ex) {
			return false;
		}
	}

}
