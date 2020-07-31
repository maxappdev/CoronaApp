package com.google.codelabs.mdc.java.shrine.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.codelabs.mdc.java.shrine.api.model.Endpoint;

import java.io.IOException;

public interface HttpClientService {
    String sendRequestToEndpoint(Endpoint endpoint) throws IOException;
    JsonNode StringToJsonObj(String string) throws IOException;
}
