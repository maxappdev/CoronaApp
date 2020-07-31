package com.google.codelabs.mdc.java.shrine.api.model;

import java.util.Date;

public class DailyReportByCountry {
    private Country country;
    private Date date;
    private long todayConfirmedNumber;
    private long todayRecoveredNumber;
    private long todayDeadNumber;
    private long overallConfirmedNumber;
    private long overallRecoveredNumber;
    private long overallDeadNumber;

    public DailyReportByCountry(Country country, Date date) {
        this.country = country;
        this.date = date;
    }

    public void setTodayNumbers(long confirmed, long recovered, long dead){
        this.todayConfirmedNumber = confirmed;
        this.todayRecoveredNumber = recovered;
        this.todayDeadNumber = dead;
    }

    public void setOverallNumbers(long confirmed, long recovered, long dead){
        this.overallConfirmedNumber = confirmed;
        this.overallRecoveredNumber = recovered;
        this.overallDeadNumber = dead;
    }

    public Country getCountry() {
        return country;
    }

    public long getOverallNumbersSum() {
        return this.overallConfirmedNumber 
                + this.overallRecoveredNumber 
                + this.overallDeadNumber;
    }
}
