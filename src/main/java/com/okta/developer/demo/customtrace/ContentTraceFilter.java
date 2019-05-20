package com.okta.developer.demo.customtrace;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
@ConditionalOnProperty(prefix = "management.trace.http", name="enabled", matchIfMissing=true)
public class ContentTraceFilter extends OncePerRequestFilter implements Ordered {
	
	private int order = Ordered.LOWEST_PRECEDENCE;
	
	@Autowired
	protected CustomHttpTraceRepository repository;
	
	@Autowired
	protected ContentTraceManager traceManager;
	
	@Value("${management.trace.http.tracebody:false}")
	protected boolean traceBody;
	
	@Override
	public int getOrder() {
		return order;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!isRequestValid(request) || !traceBody) {
			filterChain.doFilter(request, response);
			return;
		}
		try {
			ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request, 1000);
			ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

			
			filterChain.doFilter(wrappedRequest, wrappedResponse);
			
			traceManager.updateBody(wrappedRequest, wrappedResponse);

			wrappedResponse.copyBodyToResponse();
			
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
