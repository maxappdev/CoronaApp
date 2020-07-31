package com.google.codelabs.mdc.java.shrine.api.model;

public enum Country {
    AUSTRIA("Austria", "AT"),
    ITALY("Italy", "IT");

    private String name;
    private String iso2;

    Country(String name, String iso2){
        this.name = name;
        this.iso2 = iso2;
    }

    public static Country fromISO2(String iso2) {
        Country[] countries = Country.values();
        Country result = null;

        for(Country eachCountry : countries){
            if(eachCountry.getIso2().equals(iso2)){
                result = eachCountry;
                break;
            }
        }

        return result;
    }

    public String getNameLowercase() {
        return name.toLowerCase();
    }

    public String getIso2(){
        return iso2;
    }
}
