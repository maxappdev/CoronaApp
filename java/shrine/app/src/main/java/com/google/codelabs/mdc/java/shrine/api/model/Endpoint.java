package com.google.codelabs.mdc.java.shrine.api.model;

import java.util.Map;

public class Endpoint {
    private final String link;
    private final Map<String, String> headers;

    public Endpoint(String link, Map<String, String> headers) {
        this.link = link;
        this.headers = headers;
    }

    public void addHeader(String key, String value){
        headers.put(key, value);
    }

    public String getLink() {
        return link;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
