package com.raghava.newz_.models;

import java.util.ArrayList;
import java.util.List;

public class New_model {
    private int totalResults;
    private String status;
    private ArrayList<News_Articles> articles;

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
        return articles;
    }

    public void setData(ArrayList<News_Articles> articles) {
        this.articles = articles;
    }
}
