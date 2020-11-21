package com.hayohtee.coronavirustracker.model;


public class CoronavirusData {
    
    private String country;
    private String state;
    private int currentCases;
    private int prevDayCases;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCurrentCases() {
        return currentCases;
    }

    public void setCurrentCases(int currentCases) {
        this.currentCases = currentCases;
    }

    public int getPrevDayCases() {
        return prevDayCases;
    }

    public void setPrevDayCases(int prevDayCases) {
        this.prevDayCases = prevDayCases;
    }
    
    
    
}
