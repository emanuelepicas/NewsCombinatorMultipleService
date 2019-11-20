package com.sourcesense.emanuelepicariello.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)

public class HackerNews {


    private Date time;


    private String title;

    private String source = "Hacker News";

    private String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Long time) {
        Date timeConverted = new Date(((long) time * 1000));
        this.time = timeConverted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getData() {
        return time;
    }


    public void setSource(String source) {
        this.source = "Hacker News";
    }

    public String getSource() {
        return "Hacker News";
    }

}
