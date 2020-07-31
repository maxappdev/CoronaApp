package com.google.codelabs.mdc.java.shrine.api;

import com.google.codelabs.mdc.java.shrine.api.model.Country;
import com.google.codelabs.mdc.java.shrine.api.model.DailyReportByCountry;

import java.io.IOException;
import java.text.ParseException;

public interface CoronaApiService {

    DailyReportByCountry getDailyReportByCountry(Country country) throws IOException, ParseException;

}
