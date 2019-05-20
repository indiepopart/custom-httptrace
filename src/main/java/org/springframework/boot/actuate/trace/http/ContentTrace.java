package org.springframework.boot.actuate.trace.http;

import org.springframework.security.core.Authentication;

public class ContentTrace {

	protected HttpTrace httpTrace;
	
	protected String requestBody;
	
	protected String responseBody;

	protected Authentication principal;
	
	public ContentTrace() {
		
	}
	
	public ContentTrace(HttpTrace httpTrace) {
		this.httpTrace = httpTrace;
	}

	public HttpTrace getHttpTrace() {
		return httpTrace;
	}

	public void setHttpTrace(HttpTrace httpTrace) {
		this.httpTrace = httpTrace;
		if (this.principal != null) {
			this.httpTrace.setPrincipal(principal);
			this.principal = null;
		}
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public void setPrincipal(Authentication authentication) {
		this.principal = authentication;
		
	}
	
	
	
}
