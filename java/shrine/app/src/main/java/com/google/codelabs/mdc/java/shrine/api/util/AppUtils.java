package com.google.codelabs.mdc.java.shrine.api.util;

import android.content.Context;

import com.google.codelabs.mdc.java.shrine.api.model.Country;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppUtils {
    public static Date stringToDate(String formatString, String dateString) throws ParseException {
        DateFormat format = new SimpleDateFormat(formatString, Locale.ENGLISH);
        return format.parse(dateString);
    }

    public static Country getCountryOfDeviceLocation(Context applContext){
        String countryName = applContext.getResources().getConfiguration().locale.getCountry();

        return Country.fromISO2(countryName);
    }
}
