package com.google.codelabs.mdc.java.shrine.api.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.codelabs.mdc.java.shrine.api.CoronaApiService;
import com.google.codelabs.mdc.java.shrine.api.HttpClientService;
import com.google.codelabs.mdc.java.shrine.api.model.Country;
import com.google.codelabs.mdc.java.shrine.api.model.DailyReportByCountry;
import com.google.codelabs.mdc.java.shrine.api.model.Endpoint;
import com.google.codelabs.mdc.java.shrine.api.util.AppUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.google.codelabs.mdc.java.shrine.api.info.Covid19ApiInfo.HTTP_ADDRESS;
import static com.google.codelabs.mdc.java.shrine.api.info.Covid19ApiInfo.STATUS_BY_COUNTRY_ENDPOINT;

public class Covid19ApiServiceImpl implements CoronaApiService {

    private static final String DATE_FIELD_NAME = "Date";
    private static final String CONFIRMED_FIELD_NAME = "Confirmed";
    private static final String RECOVERED_FIELD_NAME = "Recovered";
    private static final String DEATHS_FIELD_NAME = "Deaths";

    private final HttpClientService httpClientService;

    public Covid19ApiServiceImpl(HttpClientService httpClientService){
        this.httpClientService = httpClientService;
    }

    @Override
    public DailyReportByCountry getDailyReportByCountry(Country country) throws IOException, ParseException {
        Endpoint dailyReportEndpoint = buildDailyReportByCountryEndpoint(country);
        String responseString = httpClientService.sendRequestToEndpoint(dailyReportEndpoint);
        JsonNode jsonResponseObj = httpClientService.StringToJsonObj(responseString);
        ArrayNode jsonResponseArray = (ArrayNode) jsonResponseObj;

        int lastIndex = jsonResponseArray.size() - 1;
        JsonNode todayJsonNode = jsonResponseArray.get(lastIndex);
        JsonNode yesterdayJsonNode = jsonResponseArray.get(lastIndex - 1);

        return buildDailyReportByCountry(todayJsonNode, yesterdayJsonNode, country);
    }

    private DailyReportByCountry buildDailyReportByCountry(JsonNode todayNode, JsonNode yesterdayNode, Country country) throws ParseException {
        TextNode dateTextNode = (TextNode) todayNode.get(DATE_FIELD_NAME);
        String dateTimeString = dateTextNode.asText();
        String dateString = dateTimeString.substring(0, 10);

        Date date = AppUtils.stringToDate("yyyy-MM-dd", dateString);
        DailyReportByCountry result = new DailyReportByCountry(country, date);

        long overallConfirmed = getLong(todayNode, CONFIRMED_FIELD_NAME);
        long overallRecovered = getLong(todayNode, RECOVERED_FIELD_NAME);
        long overallDead = getLong(todayNode, DEATHS_FIELD_NAME);

        result.setOverallNumbers(overallConfirmed, overallRecovered, overallDead);

        long yesterdayConfirmed = getLong(yesterdayNode, CONFIRMED_FIELD_NAME);
        long yesterdayRecovered = getLong(yesterdayNode, RECOVERED_FIELD_NAME);
        long yesterdayDead = getLong(yesterdayNode, DEATHS_FIELD_NAME);
        long todayConfirmed = overallConfirmed - yesterdayConfirmed;
        long todayRecovered = overallRecovered - yesterdayRecovered;
        long todayDead = overallDead - yesterdayDead;

        result.setTodayNumbers(todayConfirmed, todayRecovered, todayDead);

        return result;
    }

    private long getLong(JsonNode node, String columnName) {
        IntNode intNode = (IntNode) node.get(columnName);

        return intNode.asLong();
    }

    private Endpoint buildDailyReportByCountryEndpoint(Country country) {
        String link = HTTP_ADDRESS + STATUS_BY_COUNTRY_ENDPOINT + country.getNameLowercase();
        Map<String, String> headers = new HashMap<>();

        return new Endpoint(link, headers);
    }
}
