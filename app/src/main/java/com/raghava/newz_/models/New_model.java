package com.raghava.newz_.models;

import java.util.ArrayList;

public class New_model {
    private int totalResults;
    private String status;
    private ArrayList<News_Articles> data;

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<News_Articles> getData() {
        return data;
    }

    public void setData(ArrayList<News_Articles> data) {
        this.data = data;
    }
}
