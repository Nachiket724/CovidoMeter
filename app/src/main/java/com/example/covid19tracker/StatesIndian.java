package com.example.covid19tracker;

public class StatesIndian {

    private String state,cases,deaths,recovered,active;

    public StatesIndian()
    {

    }

    public StatesIndian(String state, String cases, String deaths, String recovered, String active) {
        this.state = state;
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
