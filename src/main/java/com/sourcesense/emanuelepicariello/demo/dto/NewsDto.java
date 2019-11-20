package com.sourcesense.emanuelepicariello.demo.dto;


import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class NewsDto implements Comparable<NewsDto>{


    private Date time;


    private String title;


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String source;

    private String url;

    @Override
    public int compareTo(@NotNull NewsDto o) {
        return this.getTime().compareTo(o.getTime());
    }
}
