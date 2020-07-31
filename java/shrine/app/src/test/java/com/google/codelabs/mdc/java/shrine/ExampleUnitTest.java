package com.google.codelabs.mdc.java.shrine;

import com.google.codelabs.mdc.java.shrine.api.CoronaApiService;
import com.google.codelabs.mdc.java.shrine.api.HttpClientService;
import com.google.codelabs.mdc.java.shrine.api.impl.Covid19ApiServiceImpl;
import com.google.codelabs.mdc.java.shrine.api.impl.OkHttpServiceImpl;
import com.google.codelabs.mdc.java.shrine.api.model.Country;
import com.google.codelabs.mdc.java.shrine.api.model.DailyReportByCountry;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void Test_Covid19Api_DailyReportByCountry_Retrieved() throws IOException, ParseException {
        HttpClientService httpClientService = new OkHttpServiceImpl();
        CoronaApiService coronaApiService = new Covid19ApiServiceImpl(httpClientService);

        Country austria = Country.AUSTRIA;

        DailyReportByCountry austriaDailyReport = coronaApiService.getDailyReportByCountry(austria);
        Assert.assertTrue(austriaDailyReport.getCountry().equals(austria));
        Assert.assertTrue(austriaDailyReport.getOverallNumbersSum() > 0);
    }
}