package com.okta.developer.demo.customtrace;

import java.util.List;

public class ContentTraceDescriptor {

    protected List<ContentTrace> traces;

    public ContentTraceDescriptor(List<ContentTrace> traces) {
        super();
        this.traces = traces;
    }

    public List<ContentTrace> getTraces() {
        return traces;
    }

    public void setTraces(List<ContentTrace> traces) {
        this.traces = traces;
    }

}
