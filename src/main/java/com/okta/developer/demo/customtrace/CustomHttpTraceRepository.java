package com.okta.developer.demo.customtrace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "management.trace.http", name = "enabled", matchIfMissing = true)
public class CustomHttpTraceRepository implements HttpTraceRepository {

    private final List<ContentTrace> contents = new LinkedList<>();

    private ContentTraceManager traceManager;
    
    public CustomHttpTraceRepository(ContentTraceManager traceManager) {
        super();
        this.traceManager = traceManager;
    }

    @Override
    public void add(HttpTrace trace) {
        synchronized (this.contents) {
            ContentTrace contentTrace = traceManager.getTrace();
            contentTrace.setHttpTrace(trace);
            this.contents.add(0, contentTrace);
        }
    }

    @Override
    public List<HttpTrace> findAll() {
        synchronized (this.contents) {
            return contents.stream().map(ContentTrace::getHttpTrace)
                    .collect(Collectors.toList());
        }
    }

    public List<ContentTrace> findAllWithContent() {
        synchronized (this.contents) {
            return Collections.unmodifiableList(new ArrayList<>(this.contents));
        }
    }

}
