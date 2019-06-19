package com.okta.developer.demo.customtrace;

import org.springframework.boot.actuate.trace.http.HttpTrace;
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

    public Authentication getPrincipal() {
        return principal;
    }

    public HttpTrace getHttpTrace() {
        return httpTrace;
    }

    public void setHttpTrace(HttpTrace httpTrace) {
        this.httpTrace = httpTrace;
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
