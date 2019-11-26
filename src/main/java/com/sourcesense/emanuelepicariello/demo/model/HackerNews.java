package com.sourcesense.emanuelepicariello.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)

public class HackerNews {


    private Date time;

    private String title;

    private String source = HACKER_NEWS;

    private String url;

    private static final String HACKER_NEWS = "Hacker News"; //

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


    public void setSource() {
        this.source = HACKER_NEWS;
    }

    public String getSource() {
        return this.source;
    }

}
