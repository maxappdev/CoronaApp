package com.google.codelabs.mdc.java.shrine.api.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.codelabs.mdc.java.shrine.api.HttpClientService;
import com.google.codelabs.mdc.java.shrine.api.model.Endpoint;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.Map;

public class OkHttpServiceImpl implements HttpClientService {
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String sendRequestToEndpoint(Endpoint endpoint) throws IOException {
        Map<String, String> headers = endpoint.getHeaders();
        String link = endpoint.getLink();

        final Request.Builder builder = new Request.Builder()
                .url(link)
                .get();

        for(String key : headers.keySet()){
            builder.addHeader(key, headers.get(key));
        }

        Request request = builder.build();
        Response response = client.newCall(request).execute();
        ResponseBody body = response.body();

        return body.string();
    }

    @Override
    public JsonNode StringToJsonObj(String string) throws IOException {
        return mapper.readTree(string);
    }
}
